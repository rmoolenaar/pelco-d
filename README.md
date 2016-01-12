# Pelco-d motor controller

Pelco-D motor controller API using the Spotify/Apollo API framework

## Installation
```bash
mvn package
```

## Run application
```bash
java -jar target/motorcontroller.jar
```

## Test commando's
To test the commando's (currently only 'left', 'right', 'stop') do for example:
```bash
curl http://localhost:8080/left
```
and the after a few seconds to stop the PTU:
```bash
curl http://localhost:8080/stop
```
