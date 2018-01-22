FROM anapsix/alpine-java
MAINTAINER jrozen
COPY testprj-1.0-SNAPSHOT.jar /home/rozen/warehouse-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","/home/rozen/warehouse-0.0.1-SNAPSHOT.jar"]