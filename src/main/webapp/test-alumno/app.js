// =========================================================================
// BANCO DE DATOS SIMULADO (Estructurado exactamente como las entidades de Marcos)
// =========================================================================
const bancoPreguntas = [
    {
        id: 1,
        enunciado: "¿Cuántos bloques tocan directamente al bloque marcado con la letra 'A'?",
        opcionA: "3 bloques",
        opcionB: "4 bloques",
        opcionC: "5 bloques",
        opcionD: "2 bloques",
        // Aquí apuntas a las imágenes de cubos 3D reales de la base de datos
        rutaImagenCubo: "https://images.unsplash.com/photo-1603126857599-f6e157fa2fe6?w=400"
    },
    {
        id: 2,
        enunciado: "Analizando la perspectiva tridimensional, ¿cuántos ladrillos componen la base oculta de la estructura?",
        opcionA: "6 ladrillos",
        opcionB: "8 ladrillos",
        opcionC: "9 ladrillos",
        opcionD: "7 ladrillos",
        rutaImagenCubo: "https://images.unsplash.com/photo-1584622650111-993a426fbf0a?w=400"
    },
    {
        id: 3,
        enunciado: "Si se remueve el bloque superior central, ¿cuántos cubos quedan con al menos 3 caras expuestas al aire?",
        opcionA: "4 cubos",
        opcionB: "3 cubos",
        opcionC: "6 cubos",
        opcionD: "5 cubos",
        rutaImagenCubo: "https://images.unsplash.com/photo-1618005182384-a83a8bd57fbe?w=400"
    }
];

// =========================================================================
// ESTADO DE LA APLICACIÓN (CONTROL DE CLIENTE)
// =========================================================================
let currentItemIndex = 0;
let tiempoRestante = 210; // 210 segundos exigidos por la rúbrica del Test Espacial
let timerInterval = null;
let respuestasUsuario = {}; // Guarda { preguntaId: "A", ... }
let cedulaEstudiante = "";

// Elementos del DOM
const loginView = document.getElementById('login-view');
const testView = document.getElementById('test-view');
const loginForm = document.getElementById('login-form');
const studentIdInput = document.getElementById('student-id');
const studentDisplay = document.getElementById('student-display');
const timerCount = document.getElementById('timer-count');

const questionText = document.getElementById('question-text');
const questionImage = document.getElementById('question-image');
const optionsContainer = document.getElementById('options-container');
const itemProgress = document.getElementById('item-progress');
const progressBarFill = document.getElementById('progress-bar-fill');

const btnPrev = document.getElementById('btn-prev');
const btnNext = document.getElementById('btn-next');

// =========================================================================
// EVENTOS PRINCIPALES
// =========================================================================

// Evento de Login / Acceso
loginForm.addEventListener('submit', function(e) {
    e.preventDefault();
    cedulaEstudiante = studentIdInput.value.trim();

    if (cedulaEstudiante.length < 5) {
        Swal.fire('Error', 'Por favor ingrese una identificación válida.', 'error');
        return;
    }

    // Intercambio estético de vistas
    loginView.classList.add('d-none');
    testView.classList.remove('d-none');
    studentDisplay.innerText = `Evaluando Candidato: ID [${cedulaEstudiante}]`;

    // Inicializar Motor
    renderizarReactivo();
    iniciarTemporizador();
});

// Navegación: Botón Anterior
btnPrev.addEventListener('click', () => {
    if (currentItemIndex > 0) {
        currentItemIndex--;
        renderizarReactivo();
    }
});

// Navegación: Botón Siguiente / Enviar
btnNext.addEventListener('click', () => {
    if (currentItemIndex < bancoPreguntas.length - 1) {
        currentItemIndex++;
        renderizarReactivo();
    } else {
        procesarFinalizacionVoluntaria();
    }
});

// =========================================================================
// FUNCIONES DEL MOTOR DINÁMICO
// =========================================================================

function iniciarTemporizador() {
    timerInterval = setInterval(() => {
        tiempoRestante--;

        // Conversión matemática a minutos y segundos legibles
        let minutos = Math.floor(tiempoRestante / 60);
        let segundos = tiempoRestante % 60;

        timerCount.innerText = `${minutos.toString().padStart(2, '0')}:${segundos.toString().padStart(2, '0')}`;

        // Alerta de advertencia cuando quedan 30 segundos
        if (tiempoRestante === 30) {
            Swal.fire({
                title: '¡Aviso de Tiempo!',
                text: 'Te quedan únicamente 30 segundos para finalizar la prueba.',
                icon: 'warning',
                toast: true,
                position: 'top-end',
                showConfirmButton: false,
                timer: 4000
            });
        }

        // Cierre automático del test por tiempo expirado (Ciclo de Vida: Tiempo Expirado)
        if (tiempoRestante <= 0) {
            clearInterval(timerInterval);
            finalizarTestAutomatico("TIEMPO_AGOTADO");
        }
    }, 1000);
}

function renderizarReactivo() {
    const preguntaActual = bancoPreguntas[currentItemIndex];

    // Actualizar Textos e Imágenes
    questionText.innerText = `${preguntaActual.id}. ${preguntaActual.enunciado}`;
    questionImage.src = preguntaActual.rutaImagenCubo;

    // Actualizar Progreso Visual
    const totalItems = bancoPreguntas.length;
    itemProgress.innerText = `Reactivo ${currentItemIndex + 1} de ${totalItems}`;
    const porcentajeProgreso = ((currentItemIndex + 1) / totalItems) * 100;
    progressBarFill.style.width = `${porcentajeProgreso}%`;

    // Renderizar las Opciones del bloque A, B, C, D
    optionsContainer.innerHTML = '';
    const opciones = [
        { letra: 'A', texto: preguntaActual.opcionA },
        { letra: 'B', texto: preguntaActual.opcionB },
        { letra: 'C', texto: preguntaActual.opcionC },
        { letra: 'D', texto: preguntaActual.opcionD }
    ];

    opciones.forEach(opc => {
        const divCol = document.createElement('div');
        divCol.className = 'col-md-6';

        // Revisar si el estudiante ya había seleccionado esta opción previamente
        const esSeleccionada = respuestasUsuario[preguntaActual.id] === opc.letra ? 'selected' : '';

        divCol.innerHTML = `
            <div class="option-box ${esSeleccionada}" onclick="seleccionarOpcion(${preguntaActual.id}, '${opc.letra}')">
                <span class="option-letter">${opc.letra}</span>
                <span class="option-text">${opc.texto}</span>
            </div>
        `;
        optionsContainer.appendChild(divCol);
    });

    // Control de los estados de los botones de navegación
    btnPrev.disabled = currentItemIndex === 0;
    if (currentItemIndex === totalItems - 1) {
        btnNext.innerText = "Finalizar y Guardar";
        btnNext.className = "btn btn-success px-4 fw-bold";
    } else {
        btnNext.innerText = "Siguiente";
        btnNext.className = "btn btn-primary px-4 fw-bold";
    }
}

// Invocado al hacer clic sobre una caja de opción
window.seleccionarOpcion = function(preguntaId, letraSeleccionada) {
    respuestasUsuario[preguntaId] = letraSeleccionada;
    // Volver a dibujar para pintar la caja de verde instantáneamente
    renderizarReactivo();
};

// Control de flujo para finalización activa del alumno
function procesarFinalizacionVoluntaria() {
    // Validación del lado del cliente: ¿Dejó preguntas vacías?
    const totalRespondidas = Object.keys(respuestasUsuario).length;
    let mensajeFaltantes = "";

    if (totalRespondidas < bancoPreguntas.length) {
        mensajeFaltantes = ` Has respondido ${totalRespondidas} de ${bancoPreguntas.length} reactivos.`;
    }

    Swal.fire({
        title: '¿Confirmas el envío?',
        text: `¿Estás seguro de finalizar el test de Ladrillos y Cubos?${mensajeFaltantes}`,
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#198754',
        cancelButtonColor: '#6c757d',
        confirmButtonText: 'Sí, enviar respuestas',
        cancelButtonText: 'Continuar revisando'
    }).then((result) => {
        if (result.isConfirmed) {
            clearInterval(timerInterval);
            finalizarTestAutomatico("ENVIO_VOLUNTARIO");
        }
    });
}

// Procesamiento y Simulación de Persistencia en la Base de Datos Relacional
function finalizarTestAutomatico(motivo) {
    // Deshabilitar controles para evitar clics accidentales post-envío (Exigencia del diseño)
    btnPrev.disabled = true;
    btnNext.disabled = true;

    let tituloAlerta = motivo === "TIEMPO_AGOTADO" ? "¡Tiempo Expirado!" : "¡Prueba Concluida!";
    let textoAlerta = motivo === "TIEMPO_AGOTADO"
        ? "El límite de 210 segundos se completó. Las respuestas guardadas se enviaron automáticamente."
        : "Tus respuestas han sido registradas de forma exitosa en el motor BFA.";

    Swal.fire({
        title: tituloAlerta,
        text: textoAlerta,
        icon: motivo === "TIEMPO_AGOTADO" ? 'warning' : 'success',
        confirmButtonText: 'Aceptar',
        allowOutsideClick: false
    }).then(() => {
        // Simulación de envío de datos al backend (OpenXava / BD)
        console.log("--- TRANSFERENCIA DE DATOS AL MOTOR OPENXAVA ---");
        console.log("Cédula de Identidad del SujetoEvaluado:", cedulaEstudiante);
        console.log("Respuestas Estructuradas Enviadas:", respuestasUsuario);

        // Render final estético de despedida
        testView.innerHTML = `
            <div class="row justify-content-center py-5">
                <div class="col-md-7 text-center bg-white p-5 rounded shadow-lg">
                    <span style="font-size: 60px;">🚀</span>
                    <h2 class="fw-bold mt-3 text-success">¡Respuestas Almacenadas!</h2>
                    <p class="text-muted">El sistema BFA ha recibido tus respuestas. El algoritmo de baremación automatizado calculará tu puntaje directo y tu percentil correspondiente en base a tu edad.</p>
                    <hr>
                    <p class="small text-muted m-0">Módulo desarrollado por: Kellys, Raúl & Marcos.</p>
                </div>
            </div>
        `;
    });
}