@ECHO off

:: Define path variables
SET importPath=%~dp0\paths
SET mypath=%~dp0..\src
cd %mypath%

:: Grab the paths of .java files found on src
for /f %%i in (%importPath%\_java.txt) DO call :concat %%i

:: Call java Compiler, define bin path and .java routes
javac -cp %mypath%\app.java -d %mypath%\..\bin %javaFiles%

:: If an error has been fouhd, pause batch
if NOT ["%ERRORLEVEL%"]==["0"] PAUSE
EXIT


:: Methods
:: Called on for
:concat
set javaFiles=%javaFiles% %1