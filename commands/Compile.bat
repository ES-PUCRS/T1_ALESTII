:: @ECHO OFF
:: Define path variables
SET pathsFolder=%~dp0paths
SET srcPath=%pathsFolder%\..\..\src
SET binPath=%srcPath%\..\bin
cd %srcPath%

start /wait cmd /k call %pathsFolder%\project_router.bat

:: Grab the paths of .java files found on src
for /f %%i in (%pathsFolder%\_java.txt) DO call :concat %%i

if NOT exist %binPath% MKDIR %binPath%

if exist dir %binPath%\src (
	@RD /s /q %binPath%\src
)

:: Call java Compiler, define bin path and .java routes
javac -cp %srcPath%\app.java -d %binPath% %javaFiles%

:: If an error has been fouhd, pause batch
if NOT ["%ERRORLEVEL%"]==["0"] PAUSE
EXIT


:: Methods
:: Called on loop to concatenate all the java path files
:concat
set javaFiles=%javaFiles% %1