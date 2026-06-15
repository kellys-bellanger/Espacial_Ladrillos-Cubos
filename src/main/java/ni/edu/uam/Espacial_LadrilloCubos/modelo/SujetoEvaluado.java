package ni.edu.uam.Espacial_LadrilloCubos.modelo;


    import java.util.*;
import javax.persistence.*;
import org.openxava.annotations.*;

    @Entity
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
}
