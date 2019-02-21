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
           withMaven(maven : 'apache-maven-3.6.0')
           sh 'mvn clean package'
       }
       }
       stage ('Test')  {
       steps {
           withMaven(maven : 'apache-maven-3.6.0')
           sh 'mvn test'

       }
       }
}
}
