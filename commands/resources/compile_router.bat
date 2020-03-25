@ECHO OFF

	SET "resourcesPath=%~dp0"
	SET "exportPath=%resourcesPath%assets\router_files\"
	SET "importPath=%resourcesPath%assets\Annotate_Classes\"
	SET "srcPath=%resourcesPath%..\..\src\"

	if NOT exist dir %exportPath% (
		mkdir %exportPath%
	)
	

:: Remove existing paths
ECHO Cleaning existing routes
	if exist %exportPath%Compile_c.txt del /f %exportPath%Compile_c.txt
	if exist %exportPath%Compile_Java.txt del /f %exportPath%Compile_Java.txt

:: Research through the project due to find commands
START /wait cmd /k call %resourcesPath%\Commander.bat

ECHO Searching routes on src...
	:: Loop through src subfolders
	for /f "tokens=*" %%i in ('dir /b /s /a:d "%srcPath%"') do (
		:: Verify if do exist a .java file on the folder
			:: Concatenate on the .txt the new path
		if exist %%i\*.c (
			@ECHO %%i\*.c>>%exportPath%Compile_c.txt
		)
		if exist %%i\*.java (
			if exist %importPath%Command_IgnoreClass.txt (
				for /f "tokens=*" %%j in ('dir /b "%%i"') do (
					for /F "tokens=*" %%l in (%importPath%Command_IgnoreClass.txt) do (
						CALL :compare %%i %%j %%l
					)
				)
			)
			if NOT Exist %importPath%Command_IgnoreClass.txt (
				@ECHO %%i\*.java>>%exportPath%\Compile_Java.txt
				@ECHO Saved class as \*.java
			)
			@ECHO %%i
		)
	)
EXIT

:compare
	SET stringfy=%1\%2
	SET stringfy=%stringfy:*src=src%

	if %stringfy% EQU %3 (
		ECHO %stringfy%
		ECHO Tu não.
		GOTO :EOF
	)

	ECHO %stringfy% | find ".java" > nul && (
		@ECHO %1\%2>>%exportPath%Compile_Java.txt
		ECHO Saved on file as %1\%2 
		GOTO :EOF
	) || (
		ECHO Thrown out - %1\%2
	)

	GOTO :EOF