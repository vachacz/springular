springular
==========

AngularJS demo application.

Technology stack:
- Gradle 1.6
- AngularJS 1.2
- Jasmine 2.0.0 + Karma
- Spring 4.0.1
- Spring Data JPA 1.5.0
- Hibernate 4.3.1 (as JPA provider)
- QueryDsl 3.3.1
- Openshift

Application available under: https://app-springular.rhcloud.com

DONE
==========
- general project structure
- **BUILD**:
  - Gradle (1.6 instead of 1.11 due to an Openshift issue)
    - Eclipse integration via Gradle-Eclipse plugin
    - generation of `QueryDSL` classes
    - generation of project `EAR` (including `WAR` with Spring Rest service, `WAR` with AngularJS webapp)
- **BACKEND**:
  - in-memory HSQL database managed by Spring
  - Spring Framework (4.0.1)
    - java-based Spring configuration
    - rest service endpoint
      - integration tests with in memory database based on MockMvc
    - cookie based security (+login, +logout, but not yet CSRF safe)
    - aspect driven Hibernate Validator (+ custom validation messages)
    - sample GET + POST requests implementation
    - JPA layer
      - Spring Data JPA (1.5.0)
      - custom Spring Data repository
      - QueryDsl (3.3.1)
- **FRONTEND**:
  - AngularJS application (1.2)
    - angular-animation used for fluent appearence of validation messages
    - custom Angular directive
    - custom Angular filter
    - employee table with client side filter|order|paging
    - HTTP 401 handing in Angular interceptor
  - Bootstrap layout 
    - Superhero Theme - http://bootswatch.com/superhero
  - Jasmine (2.0.0) unit tests
  - Karma runner (configured within IntelliJ)
- **DEPLOYMENT**:
  - Openshift integration 
    - Url: https://app-springular.rhcloud.com
    - execution of Gradle build and automatic redeployment after `git push`
- **FUNCTIONALITY**:
  - list of employees with client side paging|filtering|order
    - create|edit employee in modal window
    - route to the salary list of a given employee
  - list of salaries available only for authenticated users
    - server side filter|sort
  - login + logout via navbar
    - use admin/admin credentials to sing in

TODO
==========
- security
  - simple permission system (+role based, +field based?)
  - token based CSRF resitance
- logging
- RequireJS

SETUP
==========
- prerequisites: 
  - Gradle 1.6
  - Servlet container (tc-server, tomcat or similar)
  - *Eclipse*
    - execute `gradle eclipse`
    - import existing projects into workspace
    - create a server
    - add `server.war` and `server.webapp` projects to server deployments
    - *FYI: * Karma integration has not beed tested
  - ... or *IntelliJ Idea*
    - import projects as Gradle projects
    - create a server instance
    - install Karma plugin und user `karma.conf.js` file from the root directory
- start the server and publish the application
  - make sure that context-root is properly set in your http server
    - `http://localhost:<port>/angular-rest`
    - `http://localhost:<port>/angular-webapp`
- test if the application is online:
  - webservice: `http://localhost:<port>/angular-rest/users`
  - application: `http://localhost:<port>/angular-webapp/index.html`
- in order to generate QueryDsl classes execute `gradle compileJava` 
- enjoy!

DEPLOYMENT
==========
- add public ssh key to the openshift account
- add openshift remote to local git repository
  - `git remote add openshift -f ssh://530e696d5973ca8fe20003b4@app-springular.rhcl oud.com/~/git/app.git/`
- push to openshift
  - `git push openshift HEAD`
    - git push triggers a build and automatically deploys a new version of the application
