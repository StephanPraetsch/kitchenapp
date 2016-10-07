---------
| Java8 |
---------
export JAVA_HOME=/opt/mercateo/3rdparty/java8_64bit


---------
| maven |
---------

mvn archetype:create \
 -DarchetypeGroupId=org.apache.maven.archetypes \
 -DarchetypeArtifactId=maven-archetype-webapp \
 -DarchetypeVersion=1.0 \
 -DgroupId=com.mercateo \
 -DartifactId=kitchenapp \
 -Dversion=1.0-SNAPSHOT

in pom.xml Java8 Compiler nehmen: http://maven.apache.org/plugins/maven-compiler-plugin/examples/set-compiler-source-and-target.html

<project>
  [...]
  <build>
    [...]
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.2</version>
        <configuration>
          <source>1.4</source>
          <target>1.4</target>
        </configuration>
      </plugin>
    </plugins>
    [...]
  </build>
  [...]
</project>

mvn clean package

# downloading sources
mvn eclipse:eclipse -DdownloadSources=true
mvn dependency:sources

-------
| git |
------
git init


-----------
| Tomcat7 |
-----------
/opt/mercateo/3rdparty/apache-tomcat-7.0.53/
/opt/mercateo/3rdparty/tomcat7 -> apache-tomcat-7.0.53

Auch in Eclipse eintragen unter TOMCAT7_HOME = /opt/mercateo/3rdparty/tomcat7


export CATALINA_HOME=/opt/mercateo/3rdparty/tomcat7
cp ~/workspaces/workspace_agents/kitchenapp/target/kitchenapp.war /opt/mercateo/3rdparty/tomcat7/webapps/
sh /opt/mercateo/3rdparty/tomcat7/bin/catalina.sh start


----------
| Wicket |
----------

mvn archetype:generate -DarchetypeGroupId=org.apache.wicket -DarchetypeArtifactId=wicket-archetype-quickstart -DarchetypeVersion=6.18.0 -DgroupId=com.mercateo -DartifactId=kitchenapp -DarchetypeRepository=https://repository.apache.org/ -DinteractiveMode=false
cd kitchenapp
mvn eclipse:eclipse

in .classpath
<classpathentry kind="var"
                path="M2_REPO/org/apache/wicket/wicket-auth-roles/6.16.0/wicket-auth-roles-6.16.0.jar" />

-----------
| MongoDB |
-----------
# repo
# WICHTIG: mongodb-org verwenden, weil andere Repos wie mongo-10pen veraltet sind
sudo dnf config-manager --add-repo http://downloads-distro.mongodb.org/repo/redhat/os/x86_64/
sudo dnf install mongodb-org --nogpgcheck

# configuration
mkdir -p /opt/mercateo/3rdparty/mongodb/conf
mkdir -p /opt/mercateo/3rdparty/mongodb/data
mkdir -p /opt/mercateo/3rdparty/mongodb/log
cp mongodb.conf /opt/mercateo/3rdparty/mongodb/conf/mongodb.conf

# start forked
/usr/bin/mongod -f /opt/mercateo/3rdparty/mongodb/conf/mongodb.conf

# test
stephan@praetsch:~> mongo
MongoDB shell version: 2.6.1
connecting to: test
Welcome to the MongoDB shell.
For interactive help, type "help".
For more comprehensive documentation, see
        http://docs.mongodb.org/
Questions? Try the support group
        http://groups.google.com/group/mongodb-user
> show databases
admin  (empty)
local  0.078GB
> use local
switched to db local
> show collections
startup_log
system.indexes
> db.system.indexes.find();
{ "v" : 1, "key" : { "_id" : 1 }, "name" : "_id_", "ns" : "local.startup_log" }


# test data
mongo < src/main/java/com/mercateo/kitchenapp/db/mongo/reset.mongo 

# java
    <dependency>
        <groupId>org.mongodb</groupId>
        <artifactId>mongo-java-driver</artifactId>
        <version>2.10.1</version>
    </dependency>


classpath eintrage, zB
    <classpathentry kind="var" path="M2_REPO/org/mongodb/mongo-java-driver/2.10.1/mongo-java-driver-2.10.1.jar"/>

