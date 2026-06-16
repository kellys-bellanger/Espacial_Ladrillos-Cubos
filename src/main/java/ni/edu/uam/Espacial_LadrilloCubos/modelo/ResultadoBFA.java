package ni.edu.uam.Espacial_LadrilloCubos.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*; // Importamos Lombok

@Entity
@Getter @Setter // Genera automáticamente todos los Getters y Setters
@View(members="idResultado; puntajeDirecto, percentil") // Organiza la interfaz gráfica
public class ResultadoBFA {
    @Id
    @Column(length=32)
    @Required
    private String idResultado;

    @Required
    private int puntajeDirecto;

    @Required
    private int percentil;

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