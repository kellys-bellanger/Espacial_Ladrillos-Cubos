package ni.edu.uam.Espacial_LadrilloCubos.modelo;


import java.util.*;
import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.*;//Importamos Lombok

@Entity
@Getter @Setter //Se agregan los getter y setters
// Añadimos "resultados" al View para que se muestren abajo en la interfaz de OpenXava
@View(members="idUsuario, nombreCompleto; correoElectronico, contrasenia; edad")
public class SujetoEvaluado {

    @Id
    @Column(length=32)
    @Required
    private String idUsuario;

    @Column(length=100)
    @Required
    private String nombreCompleto;

    @Column(length=100)
    @Required
    private String correoElectronico;

    @Column(length=50)
    private String contrasenia;

    @Required
    private int edad;




    public void realizarTest() {
        // Aquí irá la lógica cuando el sujeto inicie un test
    }
}