springular
==========

Demo application (AngularJS + Spring REST)

DONE
==========
- project structure
- Gradle build
  - Eclipse integration via Gradle-Eclipse plugin
  - generation of project EAR (including WAR with Spring rest service, WAR with AngularJS webapp)
- BACKEND:
  - Spring Framework
    - java-based configuration
    - rest service endpoint
    - cookie based security (+login, +logout, but not yet CSRF safe)
    - aspect driven Hibernate Validator (+ custom validation messages)
    - sample GET + POST requests implementation
- FRONTEND:
  - AngularJS application
    - angular-animation used for fluent appearence of validation messages
    - example custom Angular directive
  - Bbootstrap layout 
    - Superhere Theme included `http://bootswatch.com/superhero`
- functionality
  - list of users with client side paging (based on bootstrap component)
    - user edition in modal window
  - login + logout via navbar
  - list of payments available only for logged in users

TODO
==========
- deploy on cloud
  - including spring profiles for development/deployment (when needed)
- security
  - simple permission system (+role based, +field based?)
  - token based CSRF resitance
  - user store provided by backend components
- datastore (+nosql on cloud?)
  - transaction handling
- bower, grunt, yo integration (if reasonable)
- testing
  - server side unit tests + integration tests with rest
  - client (+jasmine, +karma)
- domain driven design (if reasonable. not yet desided)
- funcionality
  - registration
- logging
- javascript (+how to structure angular controllers in project)
- handling of 401 request in Angular
- more ... TBD?

SETUP
==========
- install 
  - gradle
  - IDE: eclipse STS (including tc-server / tomcat)
- gradle eclipse
- import projects into eclipse
- create a server in eclipse
- add `server.war` and `server.webapp` projects to server deployments
- start the server and publish the application
  - make sure that context-root is properly set in your http server
    - `http://localhost:<port>/angular-rest`
    - `http://localhost:<port>/angular-webapp`
- test the stuff:
  - webservice: `http://localhost:<port>/angular-rest/users`
  - application: `http://localhost:<port>/angular-webapp/index.html`
- enjoy!
