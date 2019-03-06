# RecruitmentApplication - for developers

## Building & Deploying
* Source code and collaborations are managed through git and Github.

* The application is hosted as a live instance on the cloud at Heroku.

* Heroku will automatically build and deploy the application when the connected branch is updated.
You can connect or deploy different branches from your git-repository to run on Heroku, but master is preferred.


## The client
* The client makes use of Thymeleaf and consists of three parts: A **header**, a **footer**, and **content**.
The header and footer are templates that are consistent through the whole recruitment
application, while the content depends on which page is shown.

* The client uses Bean Validation for validating user input in the client.

## The server 
* The authentication and security of the web application is 
handled through Spring Security. Authenticated, logged in, users will only
have access only to parts restricted to their specific role. Meaning for example
that an applicant can not view or access pages restricted to recruiters.

* Java Spring is also used server side, with a Postgres database
that is hosted via Heroku and Postgres. 
Since the master branch is deployed to Heroku, the [application.properties file](src/main/resources/application.properties)
should always contain the Heroku-credentials. 

* However, when working locally changes should be made to connect to use the local database.
The local database can be created using [this file](src/main/scripts/db/create_db.sql).

* A local copy, or backups, of the heroku database, to be used by developers and for testing can be created trough Heroku at 
[this page](https://devcenter.heroku.com/articles/heroku-postgres-backups).


