@echo off
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-11.0.28.6-hotspot
set M2_HOME=D:\Documents\apache-maven-3.9.9
set PATH=%M2_HOME%\bin;%JAVA_HOME%\bin;%PATH%

cd /d d:\Desktop\lab2
mvn clean install -DskipTests
