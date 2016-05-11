#!/usr/bin/env bash
cd common/
../mvnw clean install
cd ..
./mvnw clean spring-boot:run