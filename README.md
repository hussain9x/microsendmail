# microsendmail
###### This is an HTTP microservice for sending emails. You need to have an SMTP server that this service will connect to.

### Usage

###### This application accepts http POST requests comprising of the following
###### HTTP Headers  
Content-Type (Required): Must be "application/json"
Mime-Type (Required): use "html" to send html based content, or any other value will send a simple text email (but do not leave it empty)

###### HTTP Request Content
The content must be a json object comprising of the following fields (all required):
from (single string): the sender's email address (must match one of authenticated accounts in the smtp server)
to (single string): the recepient email address.
subject: the subject of the email message.
message: the message body content. ( you need to include the <html> tags yourself in case of sending html content.)

###### To build a jar file from the source code on Linux/Unix machine, 

```
./mvnw clean package
```

###### To build the docker image 

```
docker build . -t user/repo:versionTag
```
Replace uesr/repo:versionTag with your own info.

###### The docker container will accept the following environment variables which are necessary for running this code.

```
SMTP_HOST       "This will be 'smtp' if not provided"
SMTP_PORT       "This will be '587' if not provided"
SMTP_USERNAME   "This is required if SMTP_Auth=true"
SMTP_PASSWORD   "This is required if SMTP_Auth=true"
SMTP_AUTH       "This will be 'true' if not provided"
SMTP_TLS.       "This will be 'true' if not provided"
LISTEN_PORT     "This will be '80' if not provided"
```

###### To run a container from the docker image,

```
docker run -d --name=<DESIRED_CONTAINER_NAME> -e SMTP_HOST=<YOUR_SMTP_SERVER_ADDRESS> -e SMTP_USERNAME=<USERNAME> -e SMTP_PASSWORD=<YOUR_SMTP_PASSWORD> hussain9x/microsendmail:0.4
```
Replace the environment variables' values and ContainerName with your own values.