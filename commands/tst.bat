@ECHO off

	SET commandPath=%~dp0
	SET root=%commandPath%..
	SET importPath=%commandPath%\paths
	SET binpath=%commandPath%..\bin
	cd %root%

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


:manifest
	:: If the manifest already exist, it will catch the version and the mainClass
	PAUSE
	if exist (%root%\manifest.txt)(
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

	:: If the manifest doesn't exist, this will define de version and ask the mainsClass name
	if NOT exist (%root%\manifest.txt) (
		ECHO Manifest not found. Generating a new one...
		SET version=1
		CALL :findMainClass
		CALL :generateManifest
	)

ECHO END
PAUSE
EXIT

:findMainClass
	for /F "delims=" %%a in (
		'findstr /S /I /M /C:"public static void main" *.*'
	) do (
		SET mainClass=%%a
	)

	if NOT defined mainClass (
		ECHO ERR: Main Class not found on project.
		ECHO Create one and try again.
			PAUSE
		EXIT /B 1
	)

	SET mainClass=%mainClass:.java=%
	SET mainClass=%mainClass:\=.%
	GOTO :EOF


:: CALLed on manifest to generate the manifest or update the file
:generateManifest
	ECHO Manifest-Version:%version% >%root%\manifest.txt
	ECHO Main-Class:%mainClass% >>%root%\manifest.txt
	GOTO :generateJar

:: CALLed on manifest loop to grab the manifest mainClass
:grabMainClass
	SET mainClass=%2
	if NOT defined mainClass (
		call :findMainClass
	)
	GOTO :EOF

:: CALLed on manifest loop to grab the manifest version
:grabVersion
	SET version=%2
	if NOT defined version (
		SET version=0
	)
	SET /A version+=1
	GOTO :EOF


:: CALLed on loop to concatenate all the files path
:concat
	SET classFiles=%classFiles% %1
	GOTO :EOF


:generateJar
	PAUSE