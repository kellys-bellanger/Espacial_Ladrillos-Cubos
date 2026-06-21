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
 * Sirve como clase base (padre) para aplicar herencia en los roles del sistema.
 * * @author Kellys
 * @version 1.0
 */

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Getter @Setter
public class Usuario {

    @Id
    @Column(length=20)
    @NotBlank(message="La cédula es obligatoria")
    private String idUsuario; // Aquí se escribira la cédula directamente

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
    private String contraseña;

    /**
     * Realiza el control lógico de autenticación del usuario.
     * * @param correo Correo ingresado en el login.
     * @param password Contraseña ingresada en el login.
     * @return true si las credenciales coinciden, false en caso contrario.
     */
    public boolean autenticar(String correo, String password) {
        if (this.correoElectronico == null || this.contraseña == null) return false;
        return this.correoElectronico.equals(correo) && this.contraseña.equals(password);
    }
}