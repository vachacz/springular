springular
==========

Demo application (AngularJS + Spring REST)

DONE
==========
- project structure
- gradle build (+eclipse integration)
  - generation of project EAR (inside: WAR with spring rest service, WAR with angular webapp)
- spring rest service
  - aspect driven hibernate validator (+ custom validation messages)
  - GET + POST request samples
- angular frontend
  - bootstrap layout (+theme)
  - angular-animation used for fluent validation messages
- functionality
  - list of users with client side paging (based on bootstrap component)
  - user edit in modal window 

TODO
==========
- deploy on cloud
  - including spring profiles for development/deployment (when needed)
- security (+login, +registration, +cookie handling in rest service)
  - simple permission system (+role based, +field based?)
- datastore (+nosql on cloud?)
  - transaction handling
- bower, grunt, yo integration (if reasonable)
- testing
  - server side unit tests + integration tests with rest
  - client (+jasmine, +karma)
- domain driven design (if reasonable. not yet desided)
- funcionality
  - registration + login
  - one more page with dummy content (+ some non trivial UI)
  - field marking after validation error (+background/border or +nice tooltip with validation message)
- logging
- javascript (+how to structure angular controllers in project)
- implement nd-directive for checking current location (http://stackoverflow.com/questions/16199418/how-do-i-implement-the-bootstrap-navbar-active-class-with-angular-js)
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
