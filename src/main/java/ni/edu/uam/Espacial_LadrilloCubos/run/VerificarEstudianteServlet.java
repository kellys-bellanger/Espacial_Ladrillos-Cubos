package ni.edu.uam.Espacial_LadrilloCubos.run;

import java.io.IOException;
import java.io.PrintWriter;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.openxava.jpa.XPersistence;
import ni.edu.uam.Espacial_LadrilloCubos.modelo.SujetoEvaluado;
import ni.edu.uam.Espacial_LadrilloCubos.modelo.ResultadoBFA;

@WebServlet("/verificar-estudiante")
public class VerificarEstudianteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        String cedula = request.getParameter("cedula");

        if (cedula == null || cedula.trim().isEmpty()) {
            out.print("{\"status\":\"error\", \"message\":\"Required\"}");
            return;
        }

        if ("procesar-respuestas".equals(action)) {
            guardarResultadosExamen(request, response, cedula.trim());
            return;
        }

        try {
            EntityManager em = XPersistence.getManager();
            SujetoEvaluado estudiante = em.find(SujetoEvaluado.class, cedula.trim());

            if (estudiante == null) {
                out.print("{\"status\":\"not_found\", \"message\":\"Not registered.\"}");
                return;
            }

            Query query = em.createQuery(
                    "SELECT r FROM ResultadoBFA r WHERE r.sujetoEvaluado.idUsuario = :cedula AND r.puntajeDirecto = 0"
            );
            query.setParameter("cedula", cedula.trim());
            query.setMaxResults(1);

            try {
                query.getSingleResult();
                out.print("{\"status\":\"success\", \"nombre\":\"" + estudiante.getNombreCompleto() + "\"}");
            } catch (NoResultException e) {
                out.print("{\"status\":\"no_test\", \"message\":\"No pending tests.\"}");
            }

        } catch (Exception e) {
            out.print("{\"status\":\"error\", \"message\":\"Error: " + e.getMessage() + "\"}");
        }
    }

    private void guardarResultadosExamen(HttpServletRequest request, HttpServletResponse response, String cedula)
            throws IOException {
        PrintWriter out = response.getWriter();
        String respuestasRaw = request.getParameter("respuestas");

        String[] respuestasEstudiante = { respuestasRaw };
        EntityManager em = XPersistence.getManager();
        try {
            em.getTransaction().begin();

            Query query = em.createQuery(
                    "SELECT r FROM ResultadoBFA r WHERE r.sujetoEvaluado.idUsuario = :cedula AND r.puntajeDirecto = 0"
            );
            query.setParameter("cedula", cedula);
            query.setMaxResults(1);

            ResultadoBFA resultado = (ResultadoBFA) query.getSingleResult();
            resultado.calcularPuntajeDirecto(respuestasEstudiante);
            resultado.generarBaremacionAutomatica();

            em.merge(resultado);
            em.getTransaction().commit();

            out.print("{\"status\":\"success\", \"message\":\"Saved\"}");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            out.print("{\"status\":\"error\", \"message\":\"Error: " + e.getMessage() + "\"}");
        }
    }
}