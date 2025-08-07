<img width="1365" height="719" alt="image" src="https://github.com/user-attachments/assets/9c243560-21fc-4901-986f-74526771de51" />

# Literatura Gutendex

Desarrollada en Java 21 con Spring Boot, esta aplicación es una solución profesional para explorar y gestionar literatura clásica. Integra la API de Gutendex para acceder a una extensa colección de libros y autores. Ideal para amantes de la literatura, investigadores y curiosos, ofrece funciones de búsqueda avanzada, filtrado por idiomas y gestión de datos locales.

## Características principales 🔥

✅ Búsqueda de libros por título usando la API de Gutendex  
✅ Registro en base de datos local (PostgreSQL) para consultas posteriores  
✅ Listado de libros registrados con detalles completos (título, autores, idiomas, descargas)  
✅ Filtrado por idiomas (Inglés, Español, Francés, Alemán, etc.)  
✅ Autores vivos en un año específico (búsqueda histórica)  
✅ Interfaz intuitiva por consola con menú interactivo  

## Comenzando 🚀

### 📋 Menú Principal

Al iniciar la aplicación, verás este menú interactivo:

```
*********************************
*     LITERATURA GUNTENDEX      *
*********************************

En Gutendex:
1 - Buscar libro por título

En la Base de Datos:
2 - Listar libros registrados
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Listar libros por idioma
6 - Listar libros por idioma Paginado

0 - Salir
```

---

### 1️⃣ Buscar libro por título

Busca libros en la API de Gutendex y los guarda en la base de datos local.

#### Cómo usarlo:

1. Selecciona la opción `1` en el menú principal.  
2. Ingresa el título del libro (ej. `Romeo and Juliet`).  
3. La aplicación muestra los resultados y los almacena automáticamente.

#### Ejemplo de salida:

```
Titulo: Romeo and Juliet
Autor(es): Shakespeare, William
Idioma(s): [en - Inglés]
Total de descargas: 82764
```

---

### 2️⃣ Listar libros registrados

Muestra todos los libros almacenados en la base de datos local.

#### Cómo usarlo:

1. Selecciona la opción `2` en el menú principal.  
2. Verás una lista detallada de libros con sus autores e idiomas.

#### Ejemplo de salida:

```
Titulo: Romeo and Juliet
Autor(es): Shakespeare, William
Idioma(s): [en - Inglés]
Descargas: 82764
```

---

### 3️⃣ Listar autores registrados

Muestra todos los autores almacenados en la base de datos.

#### Cómo usarlo:

1. Selecciona la opción `3` en el menú principal.  
2. Verás una lista de autores con sus años de nacimiento y fallecimiento.

#### Ejemplo de salida:

```
Autor: Shakespeare, William  
Nacimiento: 1564  
Fallecimiento: 1616  
Libros: [Romeo and Juliet, Hamlet]
```

---

### 4️⃣ Listar autores vivos en un año específico

Filtra autores que estaban vivos en un año dado.

#### Cómo usarlo:

1. Selecciona la opción `4` en el menú principal.  
2. Ingresa el año (ej. `1600`).  
3. Verás una lista de autores vivos en ese año.

#### Ejemplo de salida:

```
Autor: Shakespeare, William (1564-1616)
```

---

### 5️⃣ Listar libros por idioma

Filtra libros según su idioma.

#### Cómo usarlo:

1. Selecciona la opción `5` en el menú principal.  
2. Ingresa el código del idioma (ej. `es` para Español, `en` para Inglés).  

#### Ejemplo de entrada:

```
Idiomas disponibles: en, es, fr, de, it, pt, zh, la, fi, eo, nl, el, hu, is, pl, tl, enm  
Ingrese idioma(s) separados por comas: es,en
```

---

### 6️⃣ Listar libros por idioma (Paginado)

Muestra libros filtrados por idioma, distribuidos en páginas.

#### Cómo usarlo:

1. Selecciona la opción `6` en el menú principal.  
2. Ingresa el código del idioma (ej. `es`).  
3. Navega por páginas con 5 libros cada una.

#### Ejemplo de salida:

```
Página 1 de 3
--------
Titulo: Don Quijote  
Autor(es): Cervantes, Miguel de  
Idioma(s): [es - Español]  
Descargas: 50000

Presiona 'c' para continuar o 'q' para salir.
```

---
## 🔧 Instalación y ejecución

### 1️⃣ Clona el repositorio

```bash
git clone https://github.com/gezcarlos22/Literatura.git
cd Literatura
```

---

### 2️⃣ Configuración de PostgreSQL

Crea una base de datos llamada `literatura` y configura las credenciales en:

`src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literatura
spring.datasource.username=tu_usuario_postgres
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### 3️⃣ Ejecución del proyecto

#### 🔹 Opción A: Con Maven Wrapper

```bash
# Linux/Mac
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

#### 🔹 Opción B: Con Maven instalado

```bash
mvn spring-boot:run
```

#### 🔹 Opción C: Desde tu IDE

1. Abre el proyecto en IntelliJ IDEA o Eclipse.  
2. Ejecuta la clase principal:  
   `LiteraturaBackEndApplication.java`

---

### 4️⃣ Acceso a la aplicación

Una vez iniciada, la interfaz de consola estará disponible directamente en tu terminal.

---

💡 **Nota**: Asegúrate de tener instalado:

- Java 21 o superior  
- PostgreSQL 12+ en ejecución  
- Maven (si no usas el wrapper)

---
## Construido con 🛠️

* [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html) - Lenguaje de programación  
* [Spring Boot](https://spring.io/projects/spring-boot) - Framework backend  
* [PostgreSQL](https://www.postgresql.org/) - Base de datos  
* [Gutendex API](https://gutendex.com/) - Fuente de libros clásicos  
* [Maven](https://maven.apache.org/) - Gestión de dependencias  
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - Entorno de desarrollo  

---

## Autor ✒️

**Gez Carlos Enrique**  
📌 [GitHub](https://github.com/gezcarlos22)  
📧 Contacto: [gez.carlos.98@gmail.com]

---
