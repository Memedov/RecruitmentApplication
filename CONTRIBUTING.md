# RecruitmentApplication - for developers

## Building & Deploying
* Source code and collaborations are managed through git and Github.
* The application is hosted as a live instance on the cloud at Heroku.
* Heroku will automatically build and deploy the application as the master branch changes.

## The client
The client consists of three parts: A **header**, a **footer**, and **content**.
The header and footer are templates that are consistent through the whole recruitment
application, while the content depends on which page is shown.

The authentication and security of the web application is 
handled through Spring Security. Authenticated, logged in, users will only
have access only to parts restricted to their specific authentication. Meaning for example
that an applicant can not view or access pages restricted to recruiters.

## The server 
Java Spring is also used server side, with a PostgreSQL database
that is hosted via Heroku and PostgreSQL. A local database, used
for testing and developers can be created trough Heroku at 
[this page](https://devcenter.heroku.com/articles/heroku-postgres-backups).

