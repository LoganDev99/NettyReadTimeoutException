# Issue
After running the application for an extended period of time, the webclient call to the local wiremock server will begin to get an io.netty.handler.timeout.ReadTimeoutException.

## Requirements
Java 17
Download JMeter client at https://jmeter.apache.org/download_jmeter.cgi
Docker

## Start Spring Application

1. Start application with `mvn spring-boot:run`

## Start Wiremock Server

1. Start wiremock with `docker-compose up`

## Start Performance Test

1. Start JMeter `<path to JMeter folder you downloaded>/bin/jmeter.sh`
2. Open `myPerfTest.jmx` in JMeter
3. Click Play Button in JMeter

## Expect io.netty.handler.timeout.ReadTimeoutException

Depending on your computer specs, it may take a couple of minutes to see the error.