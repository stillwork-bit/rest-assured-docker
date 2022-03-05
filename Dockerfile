#FROM maven:3.6.3-jdk-11
#MAINTAINER TAN
#RUN mkdir -p /usr/src/tests
#WORKDIR /usr/src/tests
#ADD . /usr/src/tests
#RUN mvn clean test
FROM maven:3.6.3-jdk-11
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ADD . /usr/src/app
#RUN ping 172.17.248.33
RUN mvn clean test -D groups=testUI