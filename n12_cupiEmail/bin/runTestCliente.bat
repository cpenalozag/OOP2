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
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

java -ea -classpath ../lib/cupiEmail.jar;../test/lib/cupiEmailTest.jar;../test/lib/junit.jar;../test/lib/derby.jar junit.swingui.TestRunner uniandes.cupi2.cupiEmail.testCliente.MensajeTest
java -ea -classpath ../lib/cupiEmail.jar;../test/lib/cupiEmailTest.jar;../test/lib/junit.jar;../test/lib/derby.jar junit.swingui.TestRunner uniandes.cupi2.cupiEmail.testCliente.CorreoElectronicoTest