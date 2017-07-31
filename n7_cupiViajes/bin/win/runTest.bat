@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n7_cupiViajes
REM Autor: Equipo cupi2 2015
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd ../..

java -ea -classpath ./lib/cupiViajes.jar:./test/lib/cupiViajesTest.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiViajes.test.FechaTest
java -ea -classpath ./lib/cupiViajes.jar:./test/lib/cupiViajesTest.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiViajes.test.HotelTest
java -ea -classpath ./lib/cupiViajes.jar:./test/lib/cupiViajesTest.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiViajes.test.ReservaViajeTest
java -ea -classpath ./lib/cupiViajes.jar:./test/lib/cupiViajesTest.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiViajes.test.CupiViajesTest