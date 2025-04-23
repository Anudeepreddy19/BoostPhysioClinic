@echo off
echo Compiling test classes...
javac -cp "lib\junit-platform-console-standalone-1.10.0.jar;bin" -d bin test\ClinicSystemTest.java

echo Running tests...
java -jar lib\junit-platform-console-standalone-1.10.0.jar --class-path bin --scan-class-path
pause
