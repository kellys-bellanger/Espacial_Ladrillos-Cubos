package ni.edu.uam.Espacial_LadrilloCubos.run.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;

@Entity
@Tab(properties = "enunciado, opcionA, opcionB, opcionC, opcionD, respuestaCorrecta")
public class PreguntaCubos {

    @Id @GeneratedValue
    @Hidden
    private Long id;

    @Required
    @Column(length = 500)
    @RowStyle(style = "", property = "", value = "")
    private String enunciado;

    @Required
    private String opcionA;

    @Required
    private String opcionB;

    @Required
    private String opcionC;

    @Required
    private String opcionD;

    @Required
    private String respuestaCorrecta; // A, B, C o D

    @Required
    @Stereotype("IMAGE")
    @Column(length = 250)
    private String rutaImagenCubo;


}