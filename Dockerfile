FROM openjdk:8

ADD target/portfolio.jar .

CMD java -jar portfolio.jar --spring.profiles.active=docker