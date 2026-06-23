📐 Espacial_LadrilloCubos📝 DescripciónEspacial_LadrilloCubos es una solución de software híbrida desarrollada para digitalizar, aplicar y calificar de forma automatizada la prueba de Visualización Espacial (Ladrillos y Cubos), correspondiente a la reconocida Batería Factorial de Aptitudes (BFA).El sistema unifica dos entornos: un potente panel administrativo gestionado por OpenXava para los profesionales de la salud mental y una interfaz web personalizada, intuitiva y moderna diseñada específicamente para optimizar la experiencia de los estudiantes durante la resolución del test.🎯 ObjetivoAutomatizar por completo el ciclo de aplicación, recolección de respuestas y baremación de la prueba psicométrica BFA de Ladrillos y Cubos. Esto mitiga el margen de error humano en las calificaciones manuales, centraliza los expedientes y agiliza la entrega de resultados clínicos en entornos académicos.✨ Funcionalidades Principales⚙️ Módulo Administrativo (Psicólogo)Control de Expedientes: Registro, edición y consulta detallada de los datos de cada SujetoEvaluado.Asignación de Pruebas: Creación y vinculación en blanco de registros en la entidad ResultadoBFA.Auditoría de Baremos: Monitoreo inmediato de los puntajes consolidados tras la resolución del test.💻 Módulo del Estudiante (Examen Interactivo)Validación de Credenciales: Verificación asíncrona de la cédula mediante un Servlet dedicado para asegurar evaluaciones pendientes.Entorno Minimalista: Despliegue enfocado de los reactivos tridimensionales para evitar distracciones visuales.Envío Transaccional: Almacenamiento seguro e inmediato en base de datos al finalizar los ítems.🧮 Calificación y Baremación AutomáticaEjecución interna del método calcularPuntajeDirecto() contrastando las respuestas contra la plantilla clave.Disparo automático de generarBaremacionAutomatica() para transformar la puntuación directa a los rangos estándar del baremo del test.🛠️ Tecnologías UtilizadasCapaTecnologíasBackendJava, OpenXava Framework, JPA / Hibernate, Java Servlets (@WebServlet), MavenFrontendHTML5, CSS3 (Custom Styles), JavaScript (Async/Fetch ES6+)PersistenciaHsqlDB / PostgreSQL / MySQL (Ecosistema compatible con JPA)🏗️ Arquitectura del Sistema📦 Modelo de Datos (Capa de Persistencia)SujetoEvaluado: Entidad que indexa la identidad del alumno en evaluación.ResultadoBFA: Componente core donde se almacena el estado del test, las respuestas crudas y los métodos de cálculo matemático.PreguntaCubos & TestEspacial: Modelos de soporte estructural para los reactivos del examen.Psicologo & Usuario: Entidades encargadas de la seguridad del dominio.🎛️ Componente de Control WebVerificarEstudianteServlet: API interna interceptora que procesa las peticiones doPost del inicio de sesión estudiantil y administra los hilos transaccionales seguros a través de XPersistence.getManager().🔄 Flujo General del SistemaFragmento de códigograph TD
    A[Psicólogo registra alumno en OpenXava] --> B[Se crea asignación en ResultadoBFA con puntaje 0]
    B --> C[Estudiante digita cédula en index.html]
    C --> D[VerificarEstudianteServlet valida datos]
    D --> E[Se despliega test.html de forma interactiva]
    E --> F[Estudiante envía examen]
    F --> G[Servlet calcula puntaje y guarda cambios]
    G --> H[Resultados listos para auditoría en el Backend]
📁 Estructura Principal del ProyectoPlaintextsrc/
├── main/
│   ├── java/
│   │   └── ni.edu.uam.Espacial_LadrilloCubos/
│   │       ├── modelo/
│   │       │   ├── PreguntaCubos.java
│   │       │   ├── Psicologo.java
│   │       │   ├── ResultadoBFA.java
│   │       │   ├── SujetoEvaluado.java
│   │       │   ├── TestEspacial.java
│   │       │   └── Usuario.java
│   │       └── run/
│   │           ├── DBManager.java
│   │           ├── Espacial_LadrilloCubos.java
│   │           └── VerificarEstudianteServlet.java
│   ├── resources/
│   └── webapp/
│       ├── WEB-INF/
│       │   └── web.xml
│       ├── index.html
│       ├── index.js
│       ├── test.html
│       └── css/
│           └── estilos.css
🚀 Instalación y Ejecución📋 Requisitos PreviosJava Development Kit (JDK) 11 o superior.Apache Maven 3.x instalado y configurado.🛠️ Compilación limpia y empaquetadoPara limpiar cachés previas y compilar los mapas dinámicos de OpenXava, ejecute en su terminal:Bashmvn clean compile
mvn package
Posteriormente, inicie la aplicación ejecutando la clase principal embebida Espacial_LadrilloCubos.java.🎓 Conceptos Académicos AplicadosProgramación Orientada a Objetos (POO): Encapsulamiento riguroso de algoritmos psicométricos dentro de entidades lógicas del negocio.Mapeo Objeto-Relacional (ORM): Sincronización transparente de estados y persistencia automatizada.Despliegue Arquitectónico Híbrido: Coexistencia limpia entre vistas generadas dinámicamente por metadatos (OpenXava) e interfaces web asíncronas personalizadas de cara al usuario.👥 Equipo de DesarrolloProyecto diseñado e implementado por estudiantes de Ingeniería de la Universidad Americana (UAM).
