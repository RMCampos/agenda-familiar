@ECHO OFF

CALL compile.bat
@IF %ERRORLEVEL% NEQ 0 (
    EXIT /B 1
)

java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5757 -jar target\app-1.0.0-SNAPSHOT.jar