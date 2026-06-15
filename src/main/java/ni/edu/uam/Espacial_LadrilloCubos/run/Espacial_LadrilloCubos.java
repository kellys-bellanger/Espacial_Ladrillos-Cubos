package ni.edu.uam.Espacial_LadrilloCubos.run;

import org.openxava.util.*;

/**
 * Ejecuta esta clase para arrancar la aplicación.
 */

public class Espacial_LadrilloCubos {

	public static void main(String[] args) throws Exception {
		DBServer.start("Espacial_LadrilloCubos-db"); // Para usar tu propia base de datos comenta esta línea y configura src/main/webapp/META-INF/context.xml
		AppServer.run("Espacial_LadrilloCubos"); // Usa AppServer.run("") para funcionar en el contexto raíz
	}

}
