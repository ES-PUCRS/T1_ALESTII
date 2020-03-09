@ECHO off

	SET commandPath=%~dp0
	SET root=%commandPath%..
	SET importPath=%commandPath%\paths
	SET binPath=%root%\bin
	cd %binPath%

	:: Open a new terminal pad and run the compiler bat.
	:: This command will make the batch wait until compile finish.
	echo Compiling...
	start /wait cmd /k CALL %commandPath%\Compile.bat
	if NOT ["%ERRORLEVEL%"]==["0"] (
		ECHO Fail compiling, the program could not be deployed.
		PAUSE
		EXIT
	)

	echo Finding compiled files...
	start /wait cmd /k CALL %importPath%\build_router.bat
	
	GOTO :manifest
:: END



:: Create manifest and jar file 
:generateJar
	:: Grab the paths of .class files found on bin
	for /f %%i in (%importPath%\_class.txt) DO CALL :concat %%i

	:: Run over manifest and print file version created and main class on terminal
	echo Manifest.txt
	for /f "tokens=1,2 delims=" %%i in (%root%\manifest.txt) DO echo %%i %%j

	:: Generate jarfile
	echo Jar
	jar cvfm app.jar %root%\manifest.txt %classFiles%


	echo !---- Running app
	:: Run jar file
	start cmd /k CALL java -jar App.jar
	PAUSE
	EXIT



:: Define which is the program version
:manifest
	:: If the manifest already exist, it will catch the version and the mainClass
	if exist "%root%\manifest.txt" (
		for /f "tokens=1,2 delims=" %%i in (%root%\manifest.txt) DO (
				if defined version (
					CALL :grabMainClass %%i %%j
				)
				if NOT defined version (
					CALL :grabVersion %%i %%j
				)
			)
		CALL :generateManifest
	)

	:: If the manifest doesn't exist, this will define de version and ask the mainClass name
	if NOT exist "%root%\manifest.txt" (
		ECHO Manifest not found. Generating a new one...
		SET version=1
		CALL :findMainClass
		CALL :generateManifest
	)

ECHO ERR: Unespected error
PAUSE
EXIT /B 1


:: CALLed on manifest to generate the manifest or update the file
:generateManifest
	echo Manifest-Version: %version% >%root%\manifest.txt
	echo Main-Class: %mainClass% >>%root%\manifest.txt
	GOTO :generateJar

:findMainClass
	for /F "delims=" %%a in (
		'findstr /S /I /M /C:"public static void main" *.*'
	) do (
		SET mainClass=%%a
	)

	ECHO mainClass = %mainClass%
	PAUSE

	if NOT defined mainClass (
		ECHO ERR: Main Class not found on project.
		ECHO Create one and try again.
			PAUSE
		EXIT /B 1
	)

	SET mainClass=%mainClass:.java=%
	SET mainClass=%mainClass:\=.%
	GOTO :EOF

:: Called on manifest loop to grab the manifest mainClass
:grabMainClass
	SET mainClass=%2
	if NOT defined mainClass (
		call :findMainClass
	)
	GOTO :EOF

:: Called on manifest loop to grab the manifest version
:grabVersion
	SET version=%2
	if NOT defined version (
		SET version=0
	)
	SET /A version+=1
	GOTO :EOF


:: Called on loop to concatenate all the files path
:concat
	SET classFiles=%classFiles% %1
	GOTO :EOF