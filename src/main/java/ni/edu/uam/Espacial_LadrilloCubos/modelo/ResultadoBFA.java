package ni.edu.uam.Espacial_LadrilloCubos.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*; // Importamos Lombok

@Entity
@Getter @Setter // Genera automáticamente todos los Getters y Setters
// MEJORA: Añadí 'sujetoEvaluado' y 'testEspacial' a la vista para que aparezcan ordenados en la pantalla de OpenXava
@View(members="idResultado; sujetoEvaluado, testEspacial; puntajeDirecto, percentil")
public class ResultadoBFA {
    @Id
    @Column(length=32)
    @Required
    private String idResultado;

    @Required
    private int puntajeDirecto;

    @Required
    private int percentil;

    // =========================================================================
    // NUEVAS MEJORAS: CONECTANDO MI TRABAJO CON EL DE MIS COMPAÑEROS
    // =========================================================================

    // Relación con el alumno de mi clase (SujetoEvaluado).
    // Usamos @ManyToOne porque un estudiante puede tener varios resultados de pruebas.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario") // Se junta con la cédula (ID) del alumno
    @DescriptionsList(descriptionProperties = "nombreCompleto") // OpenXava nos crea un combo box con los nombres
    @Required // No podemos guardar un resultado sin saber de quién es
    private SujetoEvaluado sujetoEvaluado;

    // Relación con la prueba de Marco (TestEspacial).
    // Muchos resultados pueden pertenecer a un mismo tipo de test.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTest") // Se junta con el código de la prueba
    @DescriptionsList(descriptionProperties = "nombrePrueba") // Muestra el nombre de la prueba en pantalla
    @Required // Todo resultado debe saber de qué test proviene
    private TestEspacial testEspacial;

    // =========================================================================
    // MÉTODOS DE NEGOCIO ORIGINALES (CONSERVADOS)
    // =========================================================================

    /**
     * Calcula el puntaje directo con base en un arreglo de respuestas.
     * @param = parametro respuestas Arreglo de strings con las respuestas del test
     * @return El puntaje directo calculado
     */
    public int calcularPuntajeDirecto(String[] respuestas) {
        // Aquí irá la lógica para procesar las respuestas en el futuro
        return 0;
    }

    /**
     * Genera la baremación automática para obtener el percentil.
     * @return El percentil correspondiente
     */
    public int generarBaremacionAutomatica() {
        // Aquí irá la lógica para calcular el percentil según baremos estándar
        return 0;
    }
}