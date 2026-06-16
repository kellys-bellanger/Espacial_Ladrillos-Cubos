package ni.edu.uam.Espacial_LadrilloCubos.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@PrimaryKeyJoinColumn(name="idUsuario") // Conecta la tabla usando la cédula de la clase padre
@Getter @Setter
public class Psicologo extends Usuario{

    @Column(length=20)
    @NotBlank(message="El código de colegiado es obligatorio")
    @Size(min=5, max=20, message="El código de colegiado debe tener entre 5 y 20 caracteres")
    @Pattern(regexp = "^[A-Z0-9-]+$", message="El código de colegiado solo puede contener letras mayúsculas, números y guiones")
    private String codigoColegiado; // es como codigo profesional

    // metodos

    public void gestionarPreguntas() {
        // Aquí irá la lógica para que el psicólogo agregue o modifique reactivos en el TestEspacial
    }

    public void consultarResultados() {
        // Aquí irá la lógica para que el psicólogo revise el percentil obtenido por los alumnos
    }
}