SET mypath=%~dp0..\src
cd %mypath%

:: javac -d %mypath%\..\bin .\bin\buildFiles.txt

for /f "tokens=*delims= " %i in ('dir /s /b /o:g /ad /on') do javac -d %mypath%\..\bin *.java 

if NOT ["%ERRORLEVEL%"]==["0"] PAUSE