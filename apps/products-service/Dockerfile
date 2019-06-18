FROM java:8-alpine

ARG jar_file

WORKDIR /app

ADD $jar_file /app/app.jar

CMD [ "java", "-jar", "app.jar" ]
