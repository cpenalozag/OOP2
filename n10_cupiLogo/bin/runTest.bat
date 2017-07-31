@echo off
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
REM Universidad de los Andes (Bogotá - Colombia)
REM Departamento de Ingeniería de Sistemas y Computación 
REM Licenciado bajo el esquema Academic Free License version 2.1 
REM
REM Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
REM Ejercicio: n10_cupiLogo
REM Autor: Equipo Cupi2 2016
REM ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

SET CLASSPATH=

REM ---------------------------------------------------------
REM Ejecucion de las pruebas
REM ---------------------------------------------------------

cd ..
java -ea -classpath ./lib/cupiLogo.jar;./test/lib/cupiLogoTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiLogo.test.TableroLogoTest
java -ea -classpath ./lib/cupiLogo.jar;./test/lib/cupiLogoTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiLogo.test.TortugaTest
java -ea -classpath ./lib/cupiLogo.jar;./test/lib/cupiLogoTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiLogo.test.ComandoTest
java -ea -classpath ./lib/cupiLogo.jar;./test/lib/cupiLogoTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiLogo.test.ComandoSimpleTest
java -ea -classpath ./lib/cupiLogo.jar;./test/lib/cupiLogoTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiLogo.test.ComandoMoverTest
java -ea -classpath ./lib/cupiLogo.jar;./test/lib/cupiLogoTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiLogo.test.ComandoLimpiarTest
java -ea -classpath ./lib/cupiLogo.jar;./test/lib/cupiLogoTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiLogo.test.ComandoGirarTest
java -ea -classpath ./lib/cupiLogo.jar;./test/lib/cupiLogoTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiLogo.test.ComandoEscalarTest
java -ea -classpath ./lib/cupiLogo.jar;./test/lib/cupiLogoTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiLogo.test.ComandoCentrarTest
java -ea -classpath ./lib/cupiLogo.jar;./test/lib/cupiLogoTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiLogo.test.ComandoCambiarColorTest
java -ea -classpath ./lib/cupiLogo.jar;./test/lib/cupiLogoTest.jar;./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiLogo.test.ComandoActivarTrayectoriaTest