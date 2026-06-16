package ni.edu.uam.Espacial_LadrilloCubos.run.modelo;

import java.time.LocalDate;
import java.time.Period;
import javax.persistence.*;
import org.openxava.annotations.*;

@Entity
@View(members = "alumno; puntajeDirecto, percentil")
public class TestEspacial {

    @Id @GeneratedValue
    @Hidden
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Required
    private SujetoEvaluado alumno;

    @Required
    private int puntajeDirecto;

    @ReadOnly
    private Integer percentil;

    /**
     * Convierte el Puntaje Directo en un Percentil según la edad del alumno.
     * Se ejecuta automáticamente antes de guardar o actualizar el registro.
     */
    @PrePersist
    @PreUpdate
    public void generarBaremacionAutomatica() {

        try {
            // --- Validación de datos de entrada ---
            if (alumno == null || alumno.getEdad() == null) {
                throw new IllegalArgumentException(
                        "No se puede generar la baremación: el alumno no tiene "
                                + "una fecha de nacimiento registrada (historial incompleto).");
            }

            int edad = calcularEdad(alumno.getEdad());

            if (edad <= 0 || edad > 100) {
                throw new IllegalArgumentException(
                        "La edad calculada del alumno (" + edad + ") "
                                + "está fuera de un rango válido. Revisa el historial.");
            }

            // --- Búsqueda en la tabla de baremos ---
            this.percentil = buscarPercentilEnBaremo(edad, this.puntajeDirecto);

        } catch (IllegalArgumentException e) {
            // Re-lanzamos la excepción controlada: OpenXava la captura
            // y la muestra como mensaje de error al usuario, sin "congelar" la pantalla.
            throw e;

        } catch (Exception e) {
            // Cualquier otro error inesperado se convierte también en
            // una excepción controlada y entendible.
            throw new IllegalArgumentException(
                    "Error inesperado al calcular el percentil: " + e.getMessage());
        }
    }

    private int calcularEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    /**
     * Tabla lógica de baremos por tramo de edad.
     * (Estos valores son de ejemplo: en un caso real vendrían de
     * literatura psicométrica o de una tabla en base de datos.)
     */
    private int buscarPercentilEnBaremo(int edad, int puntajeDirecto) {

        if (edad >= 6 && edad <= 8) {
            return baremoInfantil(puntajeDirecto);
        } else if (edad >= 9 && edad <= 12) {
            return baremoPreadolescente(puntajeDirecto);
        } else if (edad >= 13 && edad <= 17) {
            return baremoAdolescente(puntajeDirecto);
        } else {
            return baremoAdulto(puntajeDirecto);
        }
    }

    private int baremoInfantil(int pd) {
        if (pd <= 5) return 10;
        if (pd <= 10) return 30;
        if (pd <= 15) return 50;
        if (pd <= 20) return 70;
        return 90;
    }

    private int baremoPreadolescente(int pd) {
        if (pd <= 8) return 15;
        if (pd <= 14) return 40;
        if (pd <= 20) return 60;
        if (pd <= 25) return 80;
        return 95;
    }

    private int baremoAdolescente(int pd) {
        if (pd <= 10) return 20;
        if (pd <= 18) return 45;
        if (pd <= 24) return 65;
        if (pd <= 30) return 85;
        return 99;
    }

    private int baremoAdulto(int pd) {
        if (pd <= 12) return 25;
        if (pd <= 20) return 50;
        if (pd <= 28) return 75;
        if (pd <= 34) return 90;
        return 99;
    }


}