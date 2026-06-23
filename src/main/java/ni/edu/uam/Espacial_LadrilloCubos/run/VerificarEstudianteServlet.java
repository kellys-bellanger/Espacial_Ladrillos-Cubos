package ni.edu.uam.Espacial_LadrilloCubos; // Usamos tu paquete real de la UAM

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

// Importaciones nativas de OpenXava y tus modelos locales
import org.openxava.jpa.XPersistence;
import ni.edu.uam.Espacial_LadrilloCubos.modelo.SujetoEvaluado; // Ajustado a tu estructura
import ni.edu.uam.Espacial_LadrilloCubos.modelo.ResultadoBFA;

@WebServlet("/verificar-estudiante")
public class VerificarEstudianteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String cedula = request.getParameter("cedula");

        if (cedula == null || cedula.trim().isEmpty()) {
            out.print("{\"status\":\"error\", \"message\":\"La cédula es requerida\"}");
            return;
        }

        try {
            // Ahora sí el XPersistence encontrará la base de datos porque está en el paquete correcto
            EntityManager em = XPersistence.getManager();

            // 1. Buscamos si la cédula existe en SujetoEvaluado
            SujetoEvaluado estudiante = em.find(SujetoEvaluado.class, cedula.trim());

            if (estudiante == null) {
                out.print("{\"status\":\"not_found\", \"message\":\"Tu cédula no está registrada por el psicólogo.\"}");
                return;
            }

            // 2. Buscamos si tiene el test asignado con puntaje en 0
            Query query = em.createQuery(
                    "SELECT r FROM ResultadoBFA r WHERE r.sujetoEvaluado.idUsuario = :cedula AND r.puntajeDirecto = 0"
            );
            query.setParameter("cedula", cedula.trim());
            query.setMaxResults(1);

            try {
                ResultadoBFA resultadoPending = (ResultadoBFA) query.getSingleResult();
                String idTest = resultadoPending.getTestEspacial().getIdTest();

                // Respondemos éxito si todo está en orden
                out.print("{\"status\":\"success\", \"nombre\":\"" + estudiante.getNombreCompleto() + "\", \"test\":\"" + idTest + "\"}");
            } catch (NoResultException e) {
                out.print("{\"status\":\"no_test\", \"message\":\"No tienes evaluaciones pendientes en este momento.\"}");
            }

        } catch (Exception e) {
            out.print("{\"status\":\"error\", \"message\":\"Error de conexión con la base de datos: " + e.getMessage() + "\"}");
        }
    }
}