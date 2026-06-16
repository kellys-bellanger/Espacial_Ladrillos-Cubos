package ni.edu.uam.Espacial_LadrilloCubos.run.modelo;

import javax.persistence.*;
import org.openxava.annotations.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Tab(properties = "enunciado, opcionA, opcionB, opcionC, opcionD, respuestaCorrecta")
public class PreguntaCubos {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long id;

    @Required
    @Column(length = 500)
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

    // RELACIÓN INVERSA OBLIGATORIA PARA EL MAPPEDBY DE MARCO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTest")
    @DescriptionsList(descriptionProperties = "nombrePrueba") // Muestra un combo box lindo en OpenXava
    private TestEspacial testEspacial;
}