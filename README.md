power-grid
==========

Product Development at the mountains - http://www.meetup.com/The-Bucharest-Agile-Software-Meetup-Group/events/181690432/
Test

Deployment
==========

You can deploy the application inside Glassfish using [maven-glassfish-plugin](https://maven-glassfish-plugin.java.net/).

First you must download and install and configure [Glassfish](https://glassfish.java.net/download.html) server.
Second you must instruct the maven plugin on the location of your server and configuration.
You can do so in a profile inside **~/.m2/settings.xml** like:
```
        <profile>
            <id>glassfish-local</id>

            <properties>
                <glassfish.glassfishDirectory>/home/ieugen/Programe/glassfish-4.0/glassfish
                </glassfish.glassfishDirectory>
                <glassfish.user>admin</glassfish.user>
                <glassfish.adminPassword>adminadmin</glassfish.adminPassword>
                <glassfish.domain.name>domain0</glassfish.domain.name>
                <glassfish.domain.host>localhost</glassfish.domain.host>
                <glassfish.domain.adminPort>10161</glassfish.domain.adminPort>
            </properties>
        </profile>
```

To deploy/re-deploy your application run:
```
    mvn glassfish:deploy -Pglassfish-local
    mvn glassfish:redeploy -Pglassfish-local
```


