package ni.edu.uam.Espacial_LadrilloCubos.modelo;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Required;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase que representa a un Usuario genérico dentro del sistema BFA.
 * Sirve como clase padre para aplicar herencia en los roles del sistema.
 * * @author Kellys
 */

@Entity
@Getter @Setter
public class Usuario {

    @Id // La clave primaria obligatoria
    @Column(length=9) // Tamaño de la columna en base de datos y UI
    private String idUsuario;

    @Column(length=50) @Required // Campo obligatorio para el nombre
    private String nombreCompleto;

    @Column(length=50) @Required // Obligatorio para loguearse
    private String correoElectronico;

    @Column(length=30) @Required
    private String contrasenia;

    // Lógica
    public boolean autenticar(String correo, String password) {
        return this.correoElectronico.equals(correo) && this.contrasenia.equals(password);
    }
}

