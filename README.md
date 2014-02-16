springular
==========

Demo application (AngularJS + Spring REST)

DONE
==========
- project structure
- gradle build (+ eclipse integration)
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
- security (+login, +registration, +cookie handling in rest service)
  - simple permission system (+role based, +field based?)
- datastore (+nosql on cloud?)
- bower, grunt, yo integration (if reasonable)
- testing
  - server side unit tests + integration tests with rest
  - client (+jasmine, +karma)
- domain driven design (if reasonable. not yet desided)
- funcionality
  - registration + login
  - one more page with dummy content (+ some non trivial UI)
  - field marking after validation error (+background/border or +nice tooltip with validation message)
- more ... TBD?

SETUP
==========
- install gradle + eclipse STS (including tc-server / tomcat)
- gradle eclipse
- import projects into eclipse
- create a server in eclipse
- add server.war and server.webapp projects to server deployments
- start the server and publish the application
- type localhost:<port>/angular-webapp/index.html
- enjoy !
