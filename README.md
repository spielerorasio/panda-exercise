# panda-exercise
web flux exercise non blocking consumer producer

To run this exercise on mac 

1. git pull this repository
2. cd panda-exercise
3. mvn clean install
4. java -jar target/panda-exercise-0.0.1-SNAPSHOT.jar
5. To follow on events per type : navigate to http://localhost:8080/eventsByType
6. To follow on word count      : navigate to http://localhost:8080/wordsCount


To run on windows 

1. download https://s3-us-west-1.amazonaws.com/bp-interview-artifacts/generator-windows-amd64.exe 
2. put the file in /panda-exercise/src/main/resources
3. follow the above 3-6 steps

To run on windows 

1. download https://s3-us-west-1.amazonaws.com/bp-interview-artifacts/generator-windows-amd64.exe 
2. put the file in /panda-exercise/src/main/resources
3. follow the above 3-6 steps


things to improve in my submission.
1. I would use Kafka to produce/consume the json events. 
messaging would give me the option to scale and skala would hold the volume and the burst peaks

2. Counting is done in memory with ConcurrentHashMap - generally non blocking but i would select an external cache/persistency that would keep the results.For the sake of scale and for the persistency.for example Redis key/value
the cache/persistency would required non blocking or ditrribution locks to protect the counting

3. My code was basic - since i did not work with reactive framework before. i suspect it could much shorter
maybe i did not need to create classes for subscribers and maybe the tranform could have been done better

4. Error handling - i did not treat them in any way - i guess in a real world dead letters should be treated.
