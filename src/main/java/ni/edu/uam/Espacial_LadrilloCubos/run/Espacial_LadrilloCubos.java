package ni.edu.uam.Espacial_LadrilloCubos.run;

import org.openxava.util.*;
import java.io.File;

public class Espacial_LadrilloCubos {

	public static void main(String[] args) throws Exception {
		System.setProperty("openxava.initDB", "true");

		// Ejemplo (Pon la ruta exacta de tu carpeta en tu computadora):
		String webappPath = "C:/TuUsuario/Proyectos/Espacial_LadrilloCubos/src/main/webapp";
		System.setProperty("tomcat.webapp.dir", webappPath);

		AppServer.run("");
	}
}