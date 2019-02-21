#!/bin/groovy
pipeline  {
        agent any
        environment {
  JAVA_HOME = "C:\Program Files\Java\jdk1.8.0_202"
}
        stages  {
               
                stage('SCM checkout') {
                        steps {      
                git branch: 'master', credentialsId: '64c944c0-c872-476c-a81a-37178b9c8487', url: 'https://github.com/krisha2705/Test-Project/'
                      }
                }
                stage ('env')  {
       steps {

           bat 'set'

       }
       }
    
                stage('Build') {
                  steps {
           withMaven(maven : 'apache-maven-3.6.0')
                          {
                                  bat 'set' 
                                  bat 'mvn clean package'
       }
                  }
       }
       stage ('Test')  {
       steps {
           withMaven(maven : 'apache-maven-3.6.0')
           bat 'mvn test'

       }
       }
}
}
