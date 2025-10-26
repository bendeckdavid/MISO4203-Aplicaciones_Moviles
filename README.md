# 🎵 Vinilos – App Móvil
**Proyecto académico - Ingeniería de Software para Aplicaciones Móviles (Uniandes)**  
**Equipo:** David Gutiérrez | Juan Camilo Peña | Keneth Bravo | Oscar Saraza

---

## 🧭 Descripción general

**Vinilos** es una aplicación móvil desarrollada en **Kotlin (Android Studio)** que permite consultar información sobre álbumes musicales, visualizar sus detalles y explorar metadatos asociados (comentarios, fecha de lanzamiento, sello discográfico, etc.).

El proyecto se desarrolla bajo un enfoque **ágil (Scrum)**, aplicando prácticas de **desarrollo iterativo**, **control de versiones con GitFlow** y **automatización de pruebas con Espresso**.

---

## 🧩 Objetivos del Proyecto

- Implementar una aplicación móvil nativa para Android con arquitectura **MVVM**.
- Integrar la app con una **API REST** alojada en un entorno local (Docker).
- Aplicar principios de **pruebas continuas** y automatizadas.
- Documentar las decisiones técnicas, diseño y estrategia de pruebas.

---

## ⚙️ Arquitectura del Sistema

El diseño de la aplicación se basa en el patrón **MVVM (Model-View-ViewModel)**, garantizando la separación de responsabilidades, escalabilidad y mantenibilidad del código.

### 🏗️ Capas principales

| Capa | Componentes | Descripción |
|------|--------------|-------------|
| **UI** | `AlbumsScreen`, `AlbumDetailsScreen`, `AlbumViewModel` | Gestiona el estado de la interfaz y renderiza los datos con Jetpack Compose. |
| **Data** | `AlbumRepository`, `AlbumService`, `RetrofitInstance` | Define la comunicación con la API REST y el manejo de datos remotos. |

---

## 📚 Tecnologías utilizadas

| Categoría | Herramienta / Librería |
|------------|------------------------|
| Lenguaje | Kotlin |
| Arquitectura | MVVM |
| UI Toolkit | Jetpack Compose |
| Cliente HTTP | Retrofit + GsonConverterFactory |
| Concurrencia | Kotlin Coroutines |
| Pruebas | Espresso |
| Control de versiones | Git + GitFlow |
| Documentación | Markdown (Wiki + Actas) |
| Backend | API REST (Node.js / JSON Server / Docker) |

---

## 🚀 Instalación y configuración

### 1️⃣ Clonar el repositorio
```bash
git clone https://github.com/bendeckdavid/MISO4203-Aplicaciones_Moviles.git
cd MISO4203-Aplicaciones_Moviles
```

### 2️⃣ Abrir el proyecto

1. Abre **Android Studio**.  
2. Selecciona **File → Open** y elige la carpeta del proyecto.  
3. Espera la sincronización de **Gradle**.  


### 3️⃣ Configurar conexión con el backend

La aplicación se conecta a una **API local** ejecutada en **Docker**.  
Usa la IP especial `10.0.2.2` para acceder desde el emulador Android.

**Ejemplo de configuración:**

```kotlin
object RetrofitInstance {
    private const val BASE_URL = "http://10.0.2.2:3000/"
}
```

### 4️⃣ Ejecutar la app

1. Usa un **emulador Android** (API 21 o superior).
2. Pulsa **Run ▶️** en **Android Studio**.
3. La app cargará el **catálogo de álbumes** desde el backend.

---

## 🧪 Estrategia de Pruebas

La estrategia de pruebas se centra en **pruebas E2E manuales y automatizadas con Espresso**, garantizando calidad funcional e integración estable.

### 🔹 Tipos de Pruebas

| Tipo        | Descripción                                                                 |
|--------------|------------------------------------------------------------------------------|
| **Unitarias** | Validan la lógica interna del `AlbumRepository` y `AlbumViewModel`.         |
| **Integración** | Comprueban la comunicación **API–App** mediante Retrofit.                 |
| **E2E (Espresso)** | Simulan la interacción del usuario: carga de álbumes, detalle, navegación. |
| **Manual** | Validación visual de interfaz, comportamiento y conectividad.                |

### ⚙️ Ejecución

Desde **Android Studio**:
1. Abre el proyecto.
2. Selecciona la configuración de ejecución deseada.
3. Ejecuta las pruebas con **Run > Run Tests** o usa los comandos de **Espresso** según corresponda.

---
### 🗂️ Estructura del proyecto
vinilos-app/
├── app/
│   ├── src/main/
│   │   ├── kotlin/miso/grupo12/vinilos/
│   │   │   ├── data/
│   │   │   │   ├── api/
│   │   │   │   ├── repository/
│   │   │   ├── ui/
│   │   │   │   ├── screens/albums/
│   │   │   │   ├── viewmodels/
│   │   │   │   ├── components/
│   │   │   ├── utils/
│   │   └── resources/
│   └── build.gradle
├── README.md
└── wiki/

---
### 🧱 GitFlow y ramas del proyecto
main      → versión estable (release)
develop   → rama de integración
feature/* → nuevas funcionalidades o HU
hotfix/*  → correcciones urgentes

---

## 📦 Entregables del Sprint 1

- 🧭 **Diseño arquitectónico:** Documentado en la wiki (MVVM).
- 🧪 **Estrategia de pruebas:** Plan E2E (manual y automatizado).
- 🧱 **Código fuente:** Implementación base de HU01 y HU02.

---

## 📅 Cronograma General

| Semana | Actividad              | Entregable                             |
|---------|------------------------|----------------------------------------|
| 1–2     | Fase de Inception      | Planeación, UX/UI, backlog inicial     |
| 3–4     | Sprint 1               | HU01, HU02, APK, pruebas E2E           |
| 5–6     | Sprint 2               | Integración de nuevas entidades        |
| 7–8     | Sprint 3               | Cierre del proyecto y retrospectiva    |

---

## 🧑‍💻 Contribución

- Cada **commit** debe asociarse a un **issue** o **HU**.
- Las fusiones se realizan mediante **Pull Request** con **Squash & Merge**.
- Los errores o defectos deben registrarse como **issues** con las etiquetas:
    - `bug`
    - `documentation`

---

## 🧾 Licencia

Este proyecto es de carácter **académico y educativo**, desarrollado para la materia  
**Ingeniería de Software para Aplicaciones Móviles (Uniandes)**.  
No tiene fines comerciales ni de distribución externa.