#!/bin/groovy
pipeline  {
        agent any
        stages  {
                stage('SCM checkout') {
                        steps {      
                git 'https://github.com/krisha2705/Test-Project/'
                      }
                }
                stage('Build') {
                  steps {
           withMaven(maven : 'maven-3.6.0')
           sh 'mvn clean compile'
       }
       }
       stage ('Test')  {
       steps {
           withMaven(maven : 'maven-3.6.0')
           sh 'mvn test'

       }
       }
}
}
