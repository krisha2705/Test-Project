#!/bin/groovy
def mvnHome
pipeline  {
        agent any
        stages  {
                stage('Maven specification') {
                        steps {
                def mvnHome = tool name: 'apache-maven-3.6.0', type: 'maven'
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
