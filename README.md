# microsendmail
###### This is an HTTP microservice for sending emails. You need to have an SMTP server that this service will connect to.

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
LISTEN_PORT     "This will be '80' if not provided"
```

###### To run a container from the docker image,

```
docker run -d --name ContainerName -e SMTP_HOST=smtp -e SMTP_USERNAME=username -e SMTP_PASSWORD=password user/repo:versionTag
```
Replace the environment variables' values and ContainerName with your own values.