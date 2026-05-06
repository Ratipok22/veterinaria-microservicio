SISTEMA DE GESTIÃ“N DE MASCOTAS PERDIDAS
ARQUITECTURA DE MICROSERVICIOS

--------------------------------------------------
DESCRIPCIÃ“N
--------------------------------------------------
Sistema backend desarrollado con Spring Boot basado en arquitectura
de microservicios. Permite gestionar usuarios, reportes de mascotas
(perdidas/encontradas), ubicaciones, notificaciones y difusiÃ³n.

Cada microservicio es independiente y se comunica mediante HTTP (REST).

--------------------------------------------------
TECNOLOGÃAS UTILIZADAS
--------------------------------------------------
- Java 17+
- Spring Boot 3
- Spring Data JPA
- Spring Security (JWT)
- MySQL
- Docker
- Maven
- Postman

--------------------------------------------------
ESTRUCTURA DEL PROYECTO
--------------------------------------------------
Cada microservicio es un proyecto independiente:

ms-usuarios/
ms-mascotas/
ms-ubicacion/
ms-notificaciones/
ms-difusion/

Cada uno contiene:

- controller
- service
- repository
- entity
- config

--------------------------------------------------
SEGURIDAD
--------------------------------------------------
Se utiliza autenticaciÃ³n mediante JWT.

Endpoint de login:
POST /login

Para acceder a endpoints protegidos:

Header:
Authorization: Bearer <token>

--------------------------------------------------
COMUNICACIÃ“N ENTRE MICROSERVICIOS
--------------------------------------------------
Se usa RestTemplate para validar dependencias.

Ejemplo (Mascotas -> Usuarios):

http://localhost:8180/usuarios/{idUsuario}

--------------------------------------------------
DOCKER
--------------------------------------------------
Cada microservicio corre en su propio contenedor.

Ejemplo para ejecuciÃ³n en un CMD para contenedores en Docker (tiene que hacerse para todos los microservicios):
docker run -d --name ms-mascotas -p 3307:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=mydatabase -e MYSQL_USER=myuser -e MYSQL_PASSWORD=password mysql:8

--------------------------------------------------
EJECUCIÃ“N LOCAL
--------------------------------------------------
1. Levantar MySQL (contenedores en docker ejecutandose)
2. Ejecutar cada microservicio desde el archivo backend

--------------------------------------------------
TESTING
--------------------------------------------------
1. Parar la ejecucion del microservicio
2. Modificar el archivo backendtest

Ejemplo difusiÃ³n test:

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private DifusionRepository difusionRepository;

    @Test
    void contextLoads() {
        assertThat(difusionRepository).isNotNull();
    }

    @Test
    void testGuardarDifusion() {
        Difusion difusion = new Difusion();
        difusion.setIdReporte(1L);
        difusion.setPlataforma("FACEBOOK");
        difusion.setEstado("PENDIENTE");

        Difusion guardado = difusionRepository.save(difusion);

        assertThat(guardado.getIdDifusion()).isNotNull();
        assertThat(guardado.getPlataforma()).isEqualTo("FACEBOOK");
    }
}

3. Ejecutar pruebas:

mvn clean
mvn compile
mvn test

--------------------------------------------------
ENDPOINTS PRINCIPALES
--------------------------------------------------

USUARIOS
GET     /usuarios
GET     /usuarios/{id}
POST    /usuarios
PUT     /usuarios/{id}
DELETE  /usuarios/{id}

MASCOTAS
GET     /mascotas
GET     /mascotas/{id}
POST    /mascotas
PUT     /mascotas/{id}
DELETE  /mascotas/{id}

UBICACIÃ“N
GET     /ubicaciones
POST    /ubicaciones
PUT     /ubicaciones/{id}

NOTIFICACIONES
GET     /notificaciones
POST    /notificaciones
PUT     /notificaciones/{id}

DIFUSIÃ“N
GET     /difusion
POST    /difusion

--------------------------------------------------
EJEMPLOS JSON (POSTMAN)
--------------------------------------------------

1. Se debe crear un POST para el login, el cual darÃ¡ el token correspondiente para autorizar todas las acciones (GET, POST, PUT, DELETE).
   En el encabezado debe tener: http://localhost:8180/login?user=test&encryptedPass=1234

2. Para los mÃ©todos de los microservicios el encabezado debe tener:
 
Ejemplo: http://localhost:8081/mascotas

3.Poner el bear token en autorization

4. Ejecutar los mÃ©todos (GET, POST, PUT, DELETE)

Ejemplos para mÃ©todos POST Y PUT:

USUARIO
{
  "nombre": "Juan PÃ©rez",
  "correo": "juan@email.com",
  "password": "1234",
  "telefono": "123456789",
  "rol": "dueÃ±o",
  "fecha_registro": "2026-05-05",
  "estado_cuenta": "activo"
}

MASCOTA
{
  "tipo_reporte": "perdido",
  "tipo_mascota": "perro",
  "raza": "Labrador",
  "color": "negro",
  "tamaÃ±o": "grande",
  "descripcion": "Se perdiÃ³ en el parque",
  "foto_url": "http://imagen.com/perro.jpg",
  "estado": "activo",
  "fecha_reporte": "2026-05-05",
  "idUsuario": 1
}

UBICACIÃ“N
{
  "idReporte": 1,
  "latitud": -33.4569,
  "longitud": -70.6483,
  "direccion": "Santiago Centro",
  "fecha": "2026-05-05"
}

NOTIFICACIÃ“N
{
  "idUsuario": 1,
  "mensaje": "Se encontrÃ³ una mascota similar",
  "tipo": "alerta",
  "estado": "enviada",
  "fecha_envio": "2026-05-05T12:00:00"
}

DIFUSIÃ“N
{
  "idReporte": 1,
  "plataforma": "Facebook",
  "estado": "publicado",
  "url_publicacion": "http://facebook.com/post",
  "fecha_publicacion": "2026-05-05T12:00:00"
}

--------------------------------------------------
CONSIDERACIONES
--------------------------------------------------
- Cada microservicio tiene su propia base de datos
- No existen joins entre microservicios
- Validaciones se hacen vÃ­a HTTP
- Configurar correctamente los puertos
