package ni.edu.uam.Espacial_LadrilloCubos.modelo;

import org.openxava.annotations.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Tab(properties = "idTest, nombrePrueba, tiempoLimite, cantidadItems")
public class TestEspacial {

    @Id
    @Column(length = 50)
    @Required
    private String idTest;

    @Column(length = 100)
    @Required
    private String nombrePrueba;

    /** Tiempo límite expresado en segundos */
    private int tiempoLimite;

    private int cantidadItems;

    // SE CORRIGIÓ EL @ListProperties con los nombres reales de los atributos de PreguntaCubos
    @OneToMany(
            mappedBy  = "testEspacial",
            cascade   = CascadeType.ALL,
            fetch     = FetchType.LAZY,
            orphanRemoval = true
    )
    @ListProperties("id, enunciado, rutaImagenCubo, respuestaCorrecta")
    private List<PreguntaCubos> preguntas = new ArrayList<>();

    public PreguntaCubos[] cargarPreguntas() {
        return preguntas.toArray(new PreguntaCubos[0]);
    }

    // Getters y Setters
    public String getIdTest() { return idTest; }
    public void setIdTest(String idTest) { this.idTest = idTest; }
    public String getNombrePrueba() { return nombrePrueba; }
    public void setNombrePrueba(String nombrePrueba) { this.nombrePrueba = nombrePrueba; }
    public int getTiempoLimite() { return tiempoLimite; }
    public void setTiempoLimite(int tiempoLimite) { this.tiempoLimite = tiempoLimite; }
    public int getCantidadItems() { return cantidadItems; }
    public void setCantidadItems(int cantidadItems) { this.cantidadItems = cantidadItems; }
    public List<PreguntaCubos> getPreguntas() { return preguntas; }
    public void setPreguntas(List<PreguntaCubos> preguntas) { this.preguntas = preguntas; }
}