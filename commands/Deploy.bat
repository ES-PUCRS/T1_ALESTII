@ECHO off

	SET commandPath=%~dp0
	SET root=%commandPath%..
	SET importPath=%commandPath%\paths
	SET binpath=%commandPath%..\bin

	cd %binpath%

	:: Open a new terminal pad and run the compiler bat.
	:: This command will make the batch wait until compile finish.
	echo Compiling...
	start /wait cmd /k call %commandPath%\Compile.bat
	if NOT ["%ERRORLEVEL%"]==["0"] (
		ECHO Fail compiling, the program could not be deployed.
		PAUSE
		EXIT
	)

	goto :findVersion
:: END



:: Create manifest and jar file 
:generateJar
	echo Manifest-Version: %version% >%root%\manifest.txt
	echo Main-Class: src.app >>%root%\manifest.txt

	:: Grab the paths of .java files found on src
	for /f %%i in (%importPath%\_class.txt) DO call :concat %%i

	echo Manifest.txt
	for /f "tokens=1,2 delims=" %%i in (%root%\manifest.txt) DO echo %%i %%j
	echo Jar
	:: Generate jarfile
	jar cvfm app.jar %root%\manifest.txt %classFiles%


	echo !---- Running app
	:: Run jar file
	java -jar App.jar

	PAUSE
	EXIT


:: Define which is the program version
:findVersion
	if exist (dir %root%\manifest.txt)
		for /f "tokens=1,2 delims=" %%i in (%root%\manifest.txt) DO (
				call :grabVersion %%i %%j
			)
		SET version=1
	goto :generateJar


	:: Called on loop to grab the manifest version
	:grabVersion
	set version=%2
	set /A version+=1
	goto :generateJar



:: Called on loop to concatenate all the files path
:concat
	set classFiles=%classFiles% %1