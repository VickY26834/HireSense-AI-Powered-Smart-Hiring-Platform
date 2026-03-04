FROM tomcat:9.0-jdk21

COPY hiresense.war /usr/local/tomcat/webapps/

EXPOSE 8080
