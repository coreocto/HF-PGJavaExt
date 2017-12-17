# HF-PGJavaExt

Prerequisite:
PostgreSQL 9.5 with PgAdmin3 (prefer BigSQL version)
JDK 8
Apache Maven 3.5.0

Setup PostgreSQL PL/Java:
Open an elevated command prompt and cd to PostgreSQL install directory.
Type: pgc install pljava15-pg95
Open PgAdmin and open SQL window
Type: set pljava.libjvm_location='<your-jdk-home>/jre/bin/server/jvm.dll'; --this statement is effective with current session only
Type: CREATE EXTENSION pljava;

Build & config this extension:
Edit maven.bat and change the %JAVA_HOME% & %PATH% variable
Double click on maven.bat to open a command prompt
Type: mvn compile
Type: mvn package
Now check the target directory, you should able to find a jar file.
Modify the path to jar inside sql\install.sql
Open PgAdmin and open SQL window
Paste the content of sql\install.sql to the SQL window & run
Paste the content of sql\test.sql to the SQL window & execute the statements one-by-one

If you receive no error, it means that you have config it properly.
Now Type: alter system set pljava.libjvm_location='<your-jdk-home>/jre/bin/server/jvm.dll'; --to persist the settings across restart
