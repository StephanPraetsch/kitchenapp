---------
| Java8 |
---------
export JAVA_HOME=/opt/mercateo/3rdparty/java8_64bit

-----------
| Tomcat8 |
-----------
/opt/mercateo/3rdparty/apache-tomcat-8.0.15/
/opt/mercateo/3rdparty/tomcat8 -> apache-tomcat-8.0.15

Ant?

----------
| Wicket |
----------

-------
| git |
------
git init

-----------
| MongoDB |
-----------
# repo
echo "name=MongoDB Repository
baseurl=http://downloads-distro.mongodb.org/repo/redhat/os/x86_64/
gpgcheck=0
enabled=1" > /etc/yum.repos.d/mongodb.repo

# install
# WICHTIG: mongodb-org verwenden, weil andere Repos wie mongo-10pen veraltet sind
yum -y install mongodb-org

# configuration
mkdir -p /opt/mercateo/3rdparty/mongodb/conf
mkdir -p /opt/mercateo/3rdparty/mongodb/data
mkdir -p /opt/mercateo/3rdparty/mongodb/log
cp mongod.conf /opt/mercateo/3rdparty/mongodb/conf/mongodb.conf

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


---------
| maven |
---------
mvn archetype:generate -DgroupId=com.mercateo -DartifactId=kitchenapp
[ alles mit ENTER bestätigen ]

cd kitchenapp

in pom.xml hinter dependencies und com.mercateo.HIER_MUSST_EINE_MAIN_CLASS_SEIN ersetzen
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <configuration>
                    <archive>
                        <manifest>
                            <mainClass>com.mercateo.HIER_MUSST_EINE_MAIN_CLASS_SEIN</mainClass>
                            <addClasspath>true</addClasspath>
                        </manifest>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>

mvn package
java -jar target/kitchenapp-1.0-SNAPSHOT.jar
