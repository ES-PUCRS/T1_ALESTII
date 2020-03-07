@ECHO off

SET mypath=%~dp0
cd %mypath%

%mypath%Compile.bat && cls && %mypath%Execute.bat

EXIT