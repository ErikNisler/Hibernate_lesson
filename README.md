# Ukol11_hibernate
Connecting via mysql using hibernate instead of JDBC

Main goal
--
Connecting to database via mysql using hibernate.

Code
--
Managing methods for Item class are saved in GoodsMethods interface.

Item class is entity of mysql database tagged as **@Entity**. Primary key **id** is tagged as **@Id**, **GeneratedValue**

Whole process is coded in ItemList class. Every overrided method has hibernate configuration with session factory. I used HQL for queries.

Main class is served as whole trigger of all methods from ItemList.
