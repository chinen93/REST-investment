#!/bin/bash


./mvnw clean package
docker build -t rest_investment .
docker run -p 8081:8081 rest_investment
