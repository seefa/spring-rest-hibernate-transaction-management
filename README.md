# spring-hibernate-tm-tutorial

This tutorial code used to explain Spring with Hibernate and using Transaction management for persistence layer and call CRUD operations with REST requests and specially adding HikariCP API for improvement database connection management by following tasks:

* add **ir.seefa.tutorial.spring-context**, **mysql-connector-java**, **ir.seefa.tutorial.spring-orm** **ir.seefa.tutorial.spring-webmvc** and dependencies
* add **ir.seefa.tutorial.spring-data-jpa**, **hibernate-core**, **hibernate-entitymanager** and **hibernate-validator** dependencies for implementing Persistence ORM layer.
* add **HikariCP** and **hibernate-hikaricp** dependencies for supporting Database Connection Pool feature.
* using Entity bean to add new DAO object and using @Entity and @Table annotations plus @GeneratedValue for generating table key value automatically.
* using tag libs in JSP view pages to iterate/post and formatting Java objects to HTML contents and pass HTML input forms to Java Object.
* using @InitBinder to convert String date input to java.util.Date format.

_TIP:_ Database DDL and DML SQL queries is added to **db_files** folder