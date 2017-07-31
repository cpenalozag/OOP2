#!/bin/sh
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# Universidad de los Andes (Bogot� - Colombia)
# Departamento de Ingenier�a de Sistemas y Computaci�n
# Licenciado bajo el esquema Academic Free License version 2.1
#
# Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
# Ejercicio: n7_cupiViajes
# Autor: Equipo Cupi2 2015
# ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

stty -echo

# ---------------------------------------------------------
# Borra todos los archivos generados por el build
# ---------------------------------------------------------

cd ../..
rm -rf classes/*
rm -rf lib/cupiViajes.jar

cd bin/mac

stty echo
