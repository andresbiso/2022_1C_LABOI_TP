<p align="center">
    Trabajo Práctico Integrador - Laboratorio I - UP
    <br>
    1C - 2022
    <br>
</p>

# :pencil: Table of Contents
- [Acerca De](#about)
- [Levantar Proyecto](#run_project)
- [Herramientas Utilizadas](#built_using)
- [Comentarios](#comments)
- [Autor](#author)
- [Reconocimientos](#acknowledgement)

# :information_source: Acerca De <a name = "about"></a>
- Repositorio que contiene el trabajo práctico de la materia Laboratorio I de la Universidad de Palermo.

# :wrench: Levantar Proyecto <a name = "run_project"></a>

## Database
- Crear la siguiente ruta:
```
C:/h2/db/
```
- En caso de querer usar una db preexistente, copiar esa db en este nuevo directorio.
- Correr la aplicación: "H2 Console"
- Se abrirá un navegador con el cliente web de H2
- Poner la siguiente configuración:
    - Saved Settings: Generic H2 (Embedded)
    - Setting Name: Generic H2 (Embedded)
    - Driver Class: org.h2.Driver
    - URL JDBC: jdbc:h2:file:C:/h2/db/h2db;AUTO_SERVER=TRUE
    - User Name: sa
    - Password: sa
- Hacer click en "Connect".

## Server
- Ver "Database" en esta misma sección para crear la DB a utilizar por el proyecto Java.
- Abrir Eclipse.
- Seleccionar como workspace la carpeta raíz que contiene el proyecto descargado del repositorio.
- En caso de que no tome el workspace: importar preferencias de eclipse (Ver "Preferencias de Eclipse - Carpeta .metadata")
- Si sigue sin funcionar:
    - Copiar carpeta "Ejercicio" a otro path.
    - Crear un nuevo proyecto "Java Proyect" con nombre "Ejercicio" (sin Module).
    - Copiar dentro de este nuevo proyecto los archivos de la carpeta "Ejercicio" que movimos de path.
    - Volver a importar preferencias de eclipse.
- Revisar que el .jar de h2 esté referenciado (Ver "H2 JAR - Embedded mode").
- Reiniciar eclipse y hacer click en run.

# :hammer: Herramientas Utilizadas <a name = "built_using"></a>

## Herramientas
Recomendamos utilizar [chocolatey](https://chocolatey.org/install) para instalar estos paquetes:
- [eclipse](https://community.chocolatey.org/packages/eclipse)
```
choco install eclipse
```
- [oraclejdk](https://community.chocolatey.org/packages/oraclejdk)
```
choco install oraclejdk --version 17.0.2
```
- [h2database](https://community.chocolatey.org/packages/h2database)
```
choco install h2database --version 2.1.214
```

## H2 JAR - Embedded mode
1. Ir a https://www.h2database.com/html/download.html
2. Descargar el Platform-Independent.Zip de la versión 2.1.214
3. En la carpeta /h2//bin encontraremos el archivo h2*.jar
4. Debemos agregar este archivo a la carpeta /h2 de nuestro proyecto
5. Luego debemos referenciar en el classpath ese .jar
    1. Eclipse: Botón derecho en el Project -> Properties -> Java Build Path -> Libraries tab -> Add JARs... -> Seleccionar h2*.jar

# :question: Otros Comentarios <a name = "comments"></a>
## Git Bash Terminal in Eclipse
- https://stackoverflow.com/questions/48949546/git-bash-terminal-in-eclipse-on-ms-windows
## Format File
- control + shift + f
## Autocomplete
- control + space
## Preferencias de Eclipse - Carpeta .metadata
- Exportar: File > Export > General > Preferences > eclipse-preferences.epf (crear si no existe)
- Importar: File > Import > General > Preferences > eclipse-preferences.epf
- No subir la carpeta .metadata a nuestro repositorio a menos que verdaderamente la necesitemos. 

# :speech_balloon: Autor <a name = "author"></a>
- [@andresbiso](https://github.com/andresbiso)

# :tada: Reconocimientos <a name = "acknowledgement"></a>
- https://github.com/github/gitignore
- https://gist.github.com/rxaviers/7360908 -> github emojis
