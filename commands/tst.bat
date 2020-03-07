::@ECHO OFF
SET manifestPath=%~dp0tst

goto :findVersion
:: END


:: Define which is the program version
:findVersion
if exist (dir %manifestPath%\manifest.txt)
	for /f %%i in (%manifestPath%\manifest.txt) DO (
			call :alocate %%i
		)
	SET version=1
goto :generateJar

:: Create manifest and jar file 
:generateJar
echo Manifest-Version: %version%
PAUSE
EXIT

:alocate
set version=%1
set version=%version:~18%
set /A version+=1
echo %version%
PAUSE
goto :generateJar