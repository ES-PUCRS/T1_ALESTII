@ECHO off

SET "binPath=%~dp0..\bin"
cd %binPath%

:: Execute java passing classpath
java -jar app.jar 12

PAUSE
EXIT