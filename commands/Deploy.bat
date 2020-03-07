@ECHO off

SET binpath=%~dp0..\bin
SET importPath=%~dp0paths
SET commandPath=%~dp0


	:: Grab the paths of .java files found on src
	for /f %%i in (%importPath%\_class.txt) DO call :concat %%i

goto :findVersion
:: END



:: Create manifest and jar file 
:generateJar
echo Manifest-Version: %version% >manifest.txt
echo Main-Class: src.app >>manifest.txt

echo Manifest.txt
for /f "tokens=1,2 delims=" %%i in (%binpath%\manifest.txt) DO echo %%i %%j
echo Jar
:: Generate jarfile
jar cvfm app.jar manifest.txt %classFiles%


echo Running app
:: Run jar file
java -jar App.jar

PAUSE
EXIT



:: Called on loop to grab the manifest version
:grabVersion
set version=%2
set /A version+=1
goto :generateJar


:: Define which is the program version
:findVersion
if exist (dir %binpath%\manifest.txt)
	for /f "tokens=1,2 delims=" %%i in (%binpath%\manifest.txt) DO (
			call :grabVersion %%i %%j
		)
	SET version=1
goto :generateJar


:: Called on loop to concatenate all the files path
:concat
set classFiles=%classFiles% %1