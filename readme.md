Enunciado

Partiendo del ejemplo 3, diseñar la aplicación para que cuando se seleccione una película del cuadro de texto (bien con un INTRO o bien mediante un botón 'Mostrar datos'), se visualice una tabla con los datos de la película. 

Nombre aplicacion: ejercicio1ajax

Nota: La base de datos (bdfotogramas) será la proporcionada en la plataforma en enlace de la unidad didáctica 4 en el apartado ejercicios. Pueden modificarse los datos de las tablas, y buscar archivos JPG en Internet para que jueguen el papel de fotogramas.

DataSource:
                <xa-datasource jndi-name="java:jboss/datasources/dsbdfotogramajpa" pool-name="dsbdfotogramajpa" enabled="true" use-ccm="true">
                    <xa-datasource-property name="ServerName">
                        localhost
                    </xa-datasource-property>
                    <xa-datasource-property name="DatabaseName">
                        bdfotogramajpa
                    </xa-datasource-property>
                    <driver>mysql5</driver>
                    <security>
                        <user-name>root</user-name>
                        <password>mysql</password>
                    </security>
                    <validation>
                        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
                        <background-validation>true</background-validation>
                        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
                    </validation>
                </xa-datasource>