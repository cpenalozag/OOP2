#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogotá - Colombia)
# Departamento de Ingeniería de Sistemas y Computación
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n7_cupiCava
# Autor: Equipo Cupi2 2015
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

# ---------------------------------------------------------
# Ejecución de las pruebas
# ---------------------------------------------------------

cd ../..
	
java -ea -classpath ./lib/cupiViajes.jar:./test/lib/cupiViajesTest.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiViajes.test.FechaTest
java -ea -classpath ./lib/cupiViajes.jar:./test/lib/cupiViajesTest.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiViajes.test.HotelTest
java -ea -classpath ./lib/cupiViajes.jar:./test/lib/cupiViajesTest.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiViajes.test.ReservaViajeTest
java -ea -classpath ./lib/cupiViajes.jar:./test/lib/cupiViajesTest.jar:./test/lib/junit.jar junit.swingui.TestRunner uniandes.cupi2.cupiViajes.test.CupiViajesTest

cd bin/mac

stty echo
