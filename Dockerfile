FROM openjdk:11.0.3-stretch
ADD target/homebudget.jar .
EXPOSE 8081
CMD java -jar homebudget.jar
