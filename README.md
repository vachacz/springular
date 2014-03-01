springular
==========

AngularJS demo application (Gradle + Spring REST + AngularJS + Openshift)

Application `https://app-springular.rhcloud.com`

DONE
==========
- project structure
- BUILD:
  - Gradle (1.6 instead of 1.11 due to an Openshift issue)
    - Eclipse integration via Gradle-Eclipse plugin
    - generation of project `EAR` (including `WAR` with Spring Rest service, `WAR` with AngularJS webapp)
- BACKEND:
  - Spring Framework (4.0.1)
    - java-based configuration
    - rest service endpoint
    - cookie based security (+login, +logout, but not yet CSRF safe)
    - aspect driven Hibernate Validator (+ custom validation messages)
    - sample GET + POST requests implementation
- FRONTEND:
  - AngularJS application (1.2)
    - angular-animation used for fluent appearence of validation messages
    - custom Angular directive
    - custom Angular filter
    - table with client side filter|order|paging
  - Bbootstrap layout 
    - Superhero Theme included `http://bootswatch.com/superhero`
- DEPLOYMENT:
  - Openshift integration `https://app-springular.rhcloud.com`
    - execution of Gradle build and automatic redeployment after `git push`
- FUNCTIONALITY:
  - list of employees with client side paging|filtering|order (based on bootstrap component)
    - user edition in modal window
    - route to the salary list of a given employee
  - list of salaries available only for an authenticated users
    - server side filter|sort
  - login + logout via navbar
    - use admin/admin credentials to sing in

TODO
==========
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
- domain driven design (if reasonable. not yet decided)
- funcionality
  - registration
  - new employee
- logging
- handling of 401 request in Angular
- more ... TBD?

SETUP
==========
- install 
  - Gradle 1.6
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

DEPLOYMENT
==========
- add public ssh key to the openshift account
- add openshift remote to local git repository
  - `git remote add openshift -f ssh://530e696d5973ca8fe20003b4@app-springular.rhcl oud.com/~/git/app.git/`
- push to openshift
  - `git push openshift HEAD`
    - git push triggers a build and automatically deploys a new version of the application
