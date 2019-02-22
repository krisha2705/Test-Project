#!/bin/groovy
pipeline  {
        agent any
        
        stages  {
               
                stage('SCM checkout') {
                        steps {      
                git branch: 'master', credentialsId: '64c944c0-c872-476c-a81a-37178b9c8487', url: 'https://github.com/krisha2705/Test-Project/'
                      }
                }
    
                stage('Build') {
                        steps {
           withMaven(maven : 'apache-maven-3.6.0')
                          {
                                  bat 'set'
                                  bat 'mvn -B -DskipTests clean install'
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
}
