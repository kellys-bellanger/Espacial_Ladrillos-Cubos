# 📐 Espacial_LadrilloCubos

---

## 📝 Descripción

**Espacial_LadrilloCubos** es una solución de software híbrida desarrollada para digitalizar, aplicar y calificar de forma automatizada la prueba de **Visualización Espacial (Ladrillos y Cubos)**, correspondiente a la reconocida Batería Factorial de Aptitudes (**BFA**).

El sistema unifica dos entornos: un potente panel administrativo gestionado por **OpenXava** para los profesionales de la salud mental y una interfaz web personalizada, intuitiva y moderna diseñada específicamente para optimizar la experiencia de los estudiantes durante la resolución del test.

---

## 🎯 Objetivo

Automatizar por completo el ciclo de aplicación, recolección de respuestas y baremación de la prueba psicométrica BFA de Ladrillos y Cubos. Esto mitiga el margen de error humano en las calificaciones manuales, centraliza los expedientes y agiliza la entrega de resultados clínicos en entornos académicos.

---

## ✨ Funcionalidades Principales

### ⚙️ Módulo Administrativo (Psicólogo)
* **Control de Expedientes:** Registro, edición y consulta detallada de los datos de cada `SujetoEvaluado`.
* **Asignación de Pruebas:** Creación y vinculación en blanco de registros en la entidad `ResultadoBFA`.
* **Auditoría de Baremos:** Monitoreo inmediato de los puntajes consolidados tras la resolución del test.

### 💻 Módulo del Estudiante (Examen Interactivo)
* **Validación de Credenciales:** Verificación asíncrona de la cédula mediante un Servlet dedicado para asegurar evaluaciones pendientes.
* **Entorno Minimalista:** Despliegue enfocado de los reactivos tridimensionales para evitar distracciones visuales.
* **Envío Transaccional:** Almacenamiento seguro e inmediato en base de datos al finalizar los ítems.

### 🧮 Calificación y Baremación Automática
* Ejecución interna del método `calcularPuntajeDirecto()` contrastando las respuestas contra la plantilla clave.
* Disparo automático de `generarBaremacionAutomatica()` para transformar la puntuación directa a los rangos estándar del baremo del test.

---

## 🛠️ Tecnologías Utilizadas

* **Backend:** Java, OpenXava Framework, JPA / Hibernate, Java Servlets (`@WebServlet`), Maven.
* **Frontend:** HTML5, CSS3 (Custom Styles), JavaScript (Async/Fetch ES6+).
* **Persistencia:** HsqlDB / PostgreSQL / MySQL (Ecosistema compatible con JPA).

---

## 🏗️ Arquitectura del Sistema

### 📦 Modelo de Datos (Capa de Persistencia)
* **`SujetoEvaluado`:** Entidad que indexa la identidad del alumno en evaluación.
* **`ResultadoBFA`:** Componente core donde se almacena el estado del test, las respuestas crudas y los métodos de cálculo matemático.
* **`PreguntaCubos` & `TestEspacial`:** Modelos de soporte estructural para los reactivos del examen.
* **`Psicologo` & `Usuario`:** Entidades encargadas de la seguridad del dominio.

### 🎛️ Componente de Control Web
* **`VerificarEstudianteServlet`:** API interna interceptora que procesa las peticiones `doPost` del inicio de sesión estudiantil y administra los hilos transaccionales seguros a través de `XPersistence.getManager()`.
