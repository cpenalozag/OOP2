@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n12_cupiEmail
REM Autor: Camilo Alvarez Duran - 19-oct-2009
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Asegura la creación de los directorios classes y lib
REM ---------------------------------------------------------

cd ..
mkdir classes
mkdir lib

REM ---------------------------------------------------------
REM Compila las clases del directorio source
REM ---------------------------------------------------------
cd source
javac -source 1.5 -d ../classes/ uniandes/cupi2/cupiEmail/cliente/interfazCliente/*.java
javac -source 1.5 -d ../classes/ uniandes/cupi2/cupiEmail/cliente/mundo/*.java
javac -source 1.5 -d ../classes/ uniandes/cupi2/cupiEmail/comun/mundo/*.java
javac -source 1.5 -d ../classes/ uniandes/cupi2/cupiEmail/interfazServidor/*.java
javac -source 1.5 -d ../classes/ uniandes/cupi2/cupiEmail/servidor/mundo/*.java
javac -source 1.5 -d ../classes/ uniandes/cupi2/cupiEmail/servidor/mundo/comunicacion/*.java
javac -source 1.5 -d ../classes/ uniandes/cupi2/cupiEmail/servidor/mundo/excepciones/*.java
javac -source 1.5 -d ../classes/ uniandes/cupi2/cupiEmail/servidor/mundo/persistencia/*.java

REM ---------------------------------------------------------
REM Crea el archivo jar a partir de los archivos compilados
REM ---------------------------------------------------------

cd ../classes
jar cf ../lib/cupiEmail.jar uniandes/*

cd ../bin

pause
