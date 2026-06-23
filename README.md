Aquí tienes el archivo README.md adaptado al 100% con los datos exactos de tu proyecto (Espacial_LadrilloCubos), tus paquetes, tus modelos de datos y la estructura real que vimos en tus capturas.

Espacial_LadrilloCubos
Descripción
Espacial_LadrilloCubos es un sistema desarrollado para digitalizar la aplicación, corrección y baremación de la prueba de Visualización Espacial (Ladrillos y Cubos), perteneciente a la Batería Factorial de Aptitudes (BFA).

El proyecto permite registrar y gestionar sujetos evaluados, aplicar pruebas de forma digital mediante un entorno web dedicado, controlar las respuestas de los estudiantes, calcular automáticamente los puntajes directos y realizar la baremación de los resultados de manera inmediata.

La solución fue desarrollada utilizando Java, OpenXava, JPA/Hibernate, HTML5, CSS3 y JavaScript.

Objetivo
Automatizar el proceso de aplicación, calificación y baremación de la prueba de visualización espacial (Ladrillos y Cubos) de la batería BFA, eliminando el margen de error humano en los cálculos manuales, optimizando la recolección de métricas y facilitando el almacenamiento digital seguro de los expedientes psicológicos.

Funcionalidades Principales
Administración del Sistema (Módulo Psicólogo/Administrador)
Gestión de Sujetos Evaluados: Registro, modificación y consulta de alumnos a evaluar.

Gestión de Resultados BFA: Monitoreo y control de pruebas pendientes, puntajes directos y tablas de baremos calculadas.

Administración del Modelo: Mapeo y persistencia completa de las entidades asociadas a la lógica de la prueba (PreguntaCubos, TestEspacial, Usuario, Psicologo).

Aplicación de la Prueba (Módulo Estudiante)
Validación de Credenciales: Verificación en tiempo real del estado del estudiante mediante su cédula de identidad y validación de pruebas asignadas pendientes.

Interfaz Dinámica: Entorno interactivo y enfocado para la resolución de ítems visuales basados en figuras tridimensionales (bloques y cubos).

Envío Seguro: Transmisión asíncrona de respuestas hacia el servidor web.

Calificación y Baremación Automática
Cálculo Inmediato: Evaluación matemática automática de las respuestas contrastadas contra la plantilla clave del test.

Baremación Automatizada: Conversión del puntaje directo a rangos estandarizados de la prueba BFA de manera transparente en el backend.

Tecnologías Utilizadas
Backend
Java

OpenXava Framework

JPA / Hibernate (Persistencia y ORM)

Java Servlets (@WebServlet)

Maven (Gestor de Dependencias)

Frontend
HTML5

CSS3

JavaScript (ES6+)

Arquitectura del Sistema
Modelo (Entidades de Persistencia)
Las principales entidades del dominio del sistema son:

SujetoEvaluado: Representa la información personal de la persona en evaluación.

ResultadoBFA: Entidad central encargada de gestionar los puntajes y disparar los métodos calcularPuntajeDirecto() y generarBaremacionAutomatica().

PreguntaCubos: Almacena la estructura lógica de los reactivos del examen.

TestEspacial: Engloba la configuración general de la prueba.

Psicologo y Usuario: Control y segmentación de roles del sistema.

Componentes Web (Controladores)
VerificarEstudianteServlet: Actúa como API REST intermedia (doPost), interceptando las peticiones de logueo de cédulas y procesando los envíos de respuestas del examen mediante hilos transaccionales independientes administrados por XPersistence.

Flujo General del Sistema
El Psicólogo registra al alumno en la plataforma administrativa de OpenXava (/modules/SujetoEvaluado).

El Psicólogo le genera una asignación en blanco con puntajeDirecto = 0 en el módulo /modules/ResultadoBFA.

El estudiante ingresa a la interfaz web e introduce su Cédula de Identidad.

El VerificarEstudianteServlet valida la existencia del alumno y el examen con puntaje en cero.

Se despliega la prueba técnica y el alumno selecciona sus respuestas.

Al enviar el test, el Servlet recupera la cadena de respuestas, ejecuta el cálculo en caliente y realiza el commit de la transacción a la base de datos.

Los datos consolidados quedan disponibles para análisis en el panel administrativo.

Estructura Principal del Proyecto
Plaintext
src/
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
Instalación y Ejecución
Requisitos
Java 11 o superior

Maven 3.x

Motor de Base de Datos compatible con OpenXava (HsqlDB / PostgreSQL / MySQL)

Navegador Web Moderno

Compilar y Empaquetar
Para limpiar registros residuales y generar el artefacto ejecutable del proyecto, ejecute los comandos estándar del ciclo de vida de Maven:

Bash
mvn clean compile
mvn package
Posteriormente, ejecutar la aplicación mediante la clase principal incorporada Espacial_LadrilloCubos.java.

Conceptos Aplicados
Programación Orientada a Objetos (POO): Modularidad, encapsulamiento y abstracción de la lógica clínica del test en componentes Java reutilizables.

Mapeo Objeto-Relacional (ORM): Sincronización transparente de entidades Java con tablas relacionales mediante especificaciones JPA.

Arquitectura Híbrida Web: Despliegue dual que combina formularios administrativos rápidos de OpenXava con vistas interactivas personalizadas para usuarios finales.

Equipo de Desarrollo
Proyecto desarrollado por estudiantes de la Universidad Americana (UAM).

Licencia
Este proyecto es de carácter estrictamente académico, desarrollado para la asignatura de Programación Orientada a Objetos.
