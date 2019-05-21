FROM openjdk:12-alpine

ADD target/portfolio.jar .

CMD java -jar portfolio.jar --spring.profiles.active=docker