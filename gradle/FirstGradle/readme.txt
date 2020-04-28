Excercises done on ques1 module

Ques 1-> Add a gradle dependency and its related repository url.

Solution -> in build.gradle

            repositories {
                mavenCentral()
            }
            dependencies {
                testCompile group: 'junit', name: 'junit', version: '4.12'
            }

Ques 2 ->   Using java plugin, make changes in the manifest to make the jar executable.
            Using java -jar JAR_NAME, the output should be printed as "Hello World"

Solution -> jar file Ques1-1.0-SNAPSHOT.jar is created and on executing java -jar Ques1-1.0-SNAPSHOT.jar it prints hello world

Ques3 ->  Differentiate between the different dependency scopes: compile, runtime, testCompile, testRuntime
          using different dependencies being defined in your build.gradle.

Solution -> i have defined various dependencies in build.gradle

           dependencies {
               testCompile group: 'junit', name: 'junit', version: '4.12'
               compile group: 'joda-time', name: 'joda-time', version: '2.10.5'
               runtime group: 'commons-collections' ,name: 'commons-collections' ,version: '3.2.2'
               testRuntime group: 'commons-lang' ,name: 'commons-lang' ,version: '2.5'
           }
           testCompile are required for testing purpose during compilation
           compile dependencies are required while compiling
           runtime dependencies are required while executing the project
           testRuntime are required for testing purpose

Ques4 -> Create a custom plugin which contains a custom task which prints the current date-time.
         Using that plugin in your project, execute that task after the jar task executes.

Solution -> I have created a custom plugin named A and a task abc. In plugin A there is a task named myTask when i execute
            it firstly hello is printed and then date

Ques5 -> Instead of using default source set, use src/main/javaCode1, src/main/javaCode2 to be taken as code source.
         Make sure that the JAR created contains files from both the directories and not from src/main/java.

Solution -> in src/main there are two more directories javacode1 and javacode2 and when i run the following command
             jar cf myJar.jar src/main/javacode1/javafile1 src/main/javacode2/javafile2
             a new jar is created named myJar.jar

Ques 6 -> Override the Gradle Wrapper task to install a different version of gradle.
          Make sure that the task written in Q4 also executes with it.

Solution -> i have changed the version from 5.2.1 to 4.2.1
            distributionUrl=https\://services.gradle.org/distributions/gradle-4.2.1-bin.zip

Ques 7-> Run the gradle profile command and attach the resulting files.

Solution -> command is gradle --profile
