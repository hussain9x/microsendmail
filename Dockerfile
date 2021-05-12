FROM openjdk:14-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV SMTP_HOST="smtp" \
SMTP_PORT=587 \
SMTP_USERNAME="" \
SMTP_PASSWORD="" \
SMTP_AUTH="true" \
LISTEN_PORT=80 \
SMTP_TLS="true"
ENTRYPOINT java -jar app.jar \
--smtp.host=$SMTP_HOST \
--smtp.port=$SMTP_PORT \
--smtp.username=$SMTP_USERNAME \
--smtp.password=$SMTP_PASSWORD \
--smtp.auth=$SMTP_AUTH \
--server.port=$LISTEN_PORT \
--smtp.tls=$SMTP_TLS