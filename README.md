# RDIS - Ration Distribution Information System

RDIS is an initiative to help organizations in maintaining information about needy people and then marking them once they have distributed data.

## Deployment

### What do you need?

1. [Install Java 8][]
    1. This program requires java version 8 or above
    2. If you already have java install, leave this step
2. [Install Microsoft SQL Server Express][]
    1. If you already have SQL Server installed you can skip this step


### Change Config file

Open following files:

    src/main/resources/config/application-dev.yml
    
    src/main/resources/config/application-prod.yml 

replace `change_this_password` with password of your SQL server `sa` user

### Create a database

Create a database locally with name `rdis`

### Packaging as jar

To build the final jar and optimize the RDIS application for production, run:

Go to folder where you have cloned / copied the code and run following command:

    ./mvnw -Pprod clean verify

or on windows

    mvnw.cmd -Pprod clean verify

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references these new files.
To ensure everything worked, run:

    java -jar target/*.jar

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.

### Packaging as war

To package your application as a war in order to deploy it to an application server (tomcat etc.), Go to folder where you have cloned / copied the code and run following command:

    ./mvnw -Pprod,war clean verify
    
or on windows

    mvnw.cmd -Pprod,war clean verify
    
    
### Change Admin Password

1. Open [http://localhost:8080](http://localhost:8080)  in your browser and change Admin password
2. default username is `admin` and password is `admin`
3. Once you login change the password to a strong password

[jhipster homepage and latest documentation]: https://www.jhipster.tech
[jhipster 6.8.0 archive]: https://www.jhipster.tech/documentation-archive/v6.8.0
[using jhipster in development]: https://www.jhipster.tech/documentation-archive/v6.8.0/development/
[using docker and docker-compose]: https://www.jhipster.tech/documentation-archive/v6.8.0/docker-compose
[using jhipster in production]: https://www.jhipster.tech/documentation-archive/v6.8.0/production/
[running tests page]: https://www.jhipster.tech/documentation-archive/v6.8.0/running-tests/
[code quality page]: https://www.jhipster.tech/documentation-archive/v6.8.0/code-quality/
[setting up continuous integration]: https://www.jhipster.tech/documentation-archive/v6.8.0/setting-up-ci/
[node.js]: https://nodejs.org/
[yarn]: https://yarnpkg.org/
[webpack]: https://webpack.github.io/
[angular cli]: https://cli.angular.io/
[browsersync]: https://www.browsersync.io/
[jest]: https://facebook.github.io/jest/
[jasmine]: https://jasmine.github.io/2.0/introduction.html
[protractor]: https://angular.github.io/protractor/
[leaflet]: https://leafletjs.com/
[definitelytyped]: https://definitelytyped.org/
[Install Java 8]: https://www.youtube.com/watch?v=rzto4yY3pVw
[Install Microsoft SQL Server Express]: https://www.youtube.com/watch?v=E_zFM7mzFUg
