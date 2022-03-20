# Instrucciones para que funciones con jenkins

## Instalación de Selenium Firefox

<code> docker run --rm --name sfirefox -d -p 4444:4444 -p 7900:7900 --shm-size="2g" --net grid selenium/standalone-firefox:latest</code>

Luego conectar el sfirefox a la red de jenkins

<code>docker network connect "tu-red-jenkins" sfirefox</code>

Con eso y la ejecucion debiera funcionar.
