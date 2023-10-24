####
# This multidb_postgres.dockerfile is used in order to build a container that runs the Quarkus application in JVM mode
#
# Before building the container image run:
#
# ./mvnw package
#
# Then, build the image with:
#
# docker build -f infra/docker/duck.dockerfile -t host:port/duckapi-jvm:latest .
# docker push host:port/duckapi-jvm:latest
#
# Build in raspberry pi
# docker buildx build --platform linux/arm64 -f infra/docker/duck.dockerfile -t host:port/duckapi-jvm:pi .
# docker push host:port/duckapi-jvm:pi
#
# Then run the container using:
#
# docker run -i --rm -p 9999:8080 duck/api-bot
#
# If you want to include the debug port into your docker image
# you will have to expose the debug port (default 5005) like this :  EXPOSE 8080 5005
#
# Then run the container using :
#
# docker run -i --rm -p 9999:8080 -p 5005:5005 -e JAVA_ENABLE_DEBUG="true" duck/api-bot
#
###
FROM registry.access.redhat.com/ubi8/openjdk-17:1.16

ENV LANGUAGE='en_US:en'

# We make four distinct layers so if there are application changes the library layers can be re-used
COPY --chown=185 target/quarkus-app/lib/ /deployments/lib/
COPY --chown=185 target/quarkus-app/*.jar /deployments/
COPY --chown=185 target/quarkus-app/app/ /deployments/app/
COPY --chown=185 target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"