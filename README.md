# HF-PGJavaExt

  Prerequisite:
  <br/>
  PostgreSQL 9.5 with PgAdmin3 (prefer BigSQL version)
  <br/>
JDK 8
  <br/>
Apache Maven 3.5.0

Setup PostgreSQL PL/Java:<br/>
Open an elevated command prompt and cd to PostgreSQL install directory.<br/>
Type: `pgc install pljava15-pg95`<br/>
Open PgAdmin and open SQL window<br/>
Type: `set pljava.libjvm_location='<your-jdk-home>/jre/bin/server/jvm.dll';` --this statement is effective with current session only<br/>
Type: `CREATE EXTENSION pljava;`<br/>

Build & config this extension:<br/>
Edit maven.bat and change the %JAVA_HOME% & %PATH% variable<br/>
Double click on maven.bat to open a command prompt<br/>
Type: `mvn compile`<br/>
Type: `mvn package`<br/>
Now check the target directory, you should able to find a jar file.<br/>
Modify the path to jar inside sql\install.sql<br/>
Open PgAdmin and open SQL window<br/>
Paste the content of sql\install.sql to the SQL window & run<br/>
Paste the content of sql\test.sql to the SQL window & execute the statements one-by-one<br/>

If you receive no error, it means that you have config it properly.<br/>
Now Type: `alter system set pljava.libjvm_location='<your-jdk-home>/jre/bin/server/jvm.dll';` --to persist the settings across restart
