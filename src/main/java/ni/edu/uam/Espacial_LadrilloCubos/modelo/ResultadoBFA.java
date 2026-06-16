package ni.edu.uam.Espacial_LadrilloCubos.modelo;

import javax.persistence.*;

import ni.edu.uam.Espacial_LadrilloCubos.modelo.PreguntaCubos;
import ni.edu.uam.Espacial_LadrilloCubos.modelo.SujetoEvaluado;
import ni.edu.uam.Espacial_LadrilloCubos.modelo.TestEspacial;
import org.openxava.annotations.*;
import lombok.*; // Importamos Lombok para los Getters y Setters automáticos

@Entity
@Getter @Setter // Genera automáticamente todos los Getters y Setters de tus variables
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

    // Relación con el alumno (SujetoEvaluado)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    @DescriptionsList(descriptionProperties = "nombreCompleto")
    @Required
    private SujetoEvaluado sujetoEvaluado;

    // Relación con la prueba de Marco (TestEspacial)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTest")
    @DescriptionsList(descriptionProperties = "nombrePrueba")
    @Required
    private TestEspacial testEspacial;

    // =========================================================================
    // MÉTODOS DE NEGOCIO CORREGIDOS
    // =========================================================================

    /**
     * Calcula el puntaje directo con base en un arreglo de respuestas.
     * @param respuestas Arreglo de strings con las respuestas del test
     * @return El puntaje directo calculado
     */
    public int calcularPuntajeDirecto(String[] respuestas) {
        int puntosAbonados = 0;

        // Validamos que tengamos un test asignado y que tenga preguntas adentro
        if (this.testEspacial != null && this.testEspacial.getPreguntas() != null) {

            // Recorremos las respuestas del alumno con un ciclo for común
            for (int i = 0; i < respuestas.length; i++) {

                // Evitamos que revise más allá de las preguntas que existen en el examen
                if (i < this.testEspacial.getPreguntas().size()) {

                    // Obtenemos la pregunta de la lista de Marco
                    PreguntaCubos preguntaReal = this.testEspacial.getPreguntas().get(i);

                    // Comparamos la respuesta del alumno con la respuesta correcta
                    if (preguntaReal.getRespuestaCorrecta().equalsIgnoreCase(respuestas[i])) {
                        puntosAbonados++; // Si acertó, suma un punto
                    }
                }
            }
        }

        // Guardamos el resultado en la variable de la clase
        this.puntajeDirecto = puntosAbonados;
        return this.puntajeDirecto;
    }

    /**
     * Genera la baremación automática para obtener el percentil.
     * @return El percentil correspondiente
     */
    public int generarBaremacionAutomatica() {
        int percentilCalculado = 50; // Empezamos con un percentil intermedio por defecto

        // Validamos que tengamos un estudiante asignado para ver su edad
        if (this.sujetoEvaluado != null) {
            int edadAlumno = this.sujetoEvaluado.getEdad();

            // Lógica de baremos simplificada por edad
            if (edadAlumno < 12) {
                if (this.puntajeDirecto >= 15) {
                    percentilCalculado = 95;
                } else if (this.puntajeDirecto >= 10) {
                    percentilCalculado = 75;
                } else {
                    percentilCalculado = 40;
                }
            } else {
                if (this.puntajeDirecto >= 18) {
                    percentilCalculado = 99;
                } else if (this.puntajeDirecto >= 12) {
                    percentilCalculado = 80;
                } else {
                    percentilCalculado = 50;
                }
            }
        }

        // Guardamos el resultado en la variable de la clase
        this.percentil = percentilCalculado;
        return this.percentil;
    }
}