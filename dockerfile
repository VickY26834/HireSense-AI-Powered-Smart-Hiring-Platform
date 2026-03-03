FROM tomcat:9.0-jdk21

COPY hiresense2.war /usr/local/tomcat/webapps/

EXPOSE 8080
