package ni.edu.uam.Espacial_LadrilloCubos.modelo;

import lombok.Getter;
import lombok.Setter;
import org.openxava.annotations.Required;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@PrimaryKeyJoinColumn(name="idUsuario") // Se conecta a la tabla Usuario usando la cédula
@Getter @Setter
public class SujetoEvaluado extends Usuario {

    // --- ATRIBUTOS PROPIOS DEL SUJETO EVALUADO ---

    @Min(value = 4, message = "La edad mínima permitida es de 4 años")
    @Max(value = 100, message = "La edad máxima permitida es de 100 años")
    @Required // Anotación de OpenXava para que sea obligatorio en la pantalla
    private int edad;


    public void realizarTest() {
        // Aquí irá la lógica para que el sujeto inicie y responda el TestEspacial
    }
}