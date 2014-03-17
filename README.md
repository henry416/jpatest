jpatest
=======

A Standalone Java Project to test CRUD operations on a embedded Derby DB and using JPA.

There are two tests included in this project.

1) run_jpatest.sh

mvn compile exec:java -Dexec.mainClass=henry416.jpa.JpaTest

This will create two reocrds in test1.employees and one record in test1.department of a localized embedded derby database, and display the results accordingly.

2) run_studenttest.sh

mvn compile exec:java -Dexec.mainClass=henry416.jpa.StudentTest

This will test CRUD operations (Create, Read, Update, Delete) on a student object, and persist on a table test1.student of a localized embedded derby database, and display the results accordingly.

Prerequisites
=============

mavern 2
