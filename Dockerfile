FROM jetty:9

LABEL maintainer="github.com/MarinaFX"

COPY ./build/libs/Tema6.war ./webapps/

EXPOSE 8080