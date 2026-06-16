package ni.edu.uam.Espacial_LadrilloCubos.modelo;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Required;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Clase que representa a un Usuario genérico dentro del sistema BFA.
 * Sirve como clase padre para aplicar herencia en los roles del sistema.
 * * @author Kellys
 */

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Getter @Setter
public class Usuario {

    @Id
    @Column(length=20) // Longitud ideal para el formato de cédula con guiones (ej: 001-000000-0000A)
    @NotBlank(message="La cédula es obligatoria")
    private String idUsuario; // Aquí se digitará la cédula directamente

    @NotBlank(message="El nombre completo no puede quedar vacío")
    @Size(min=3, max=50, message="El nombre debe tener entre 3 y 50 caracteres")
    @Column(length=50) @Required // Campo obligatorio para el nombre
    private String nombreCompleto;

    @NotBlank(message="El correo electrónico es obligatorio")
    @Email(message="Debe ingresar un correo electrónico válido (ejemplo: usuario@dominio.com)")
    @Column(length=50) @Required // Obligatorio para loguearse
    private String correoElectronico;

    @Column(length=30)
    @Required
    private String contrasenia;

    // Lógica
    public boolean autenticar(String correo, String password) {
        if (this.correoElectronico == null || this.contrasenia == null) return false;
        return this.correoElectronico.equals(correo) && this.contrasenia.equals(password);
    }
}