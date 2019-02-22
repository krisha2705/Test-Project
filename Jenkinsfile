#!/bin/groovy
pipeline  {
        agent any
        
        stages  {
               
                stage('SCM checkout') {
                        steps {      
                git branch: 'master', credentialsId: '64c944c0-c872-476c-a81a-37178b9c8487', url: 'https://github.com/krisha2705/Test-Project/'
                      }
                }
          
                 stage ('Env')  {
                         steps {
                         bat 'set'
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
                }           
                 stage ('Test')  {
                         steps {
           withMaven(maven : 'apache-maven-3.6.0')
           bat 'mvn test'
       }
       }
                post {
        always {
            echo 'This will always run'
        }
        success {
            echo 'This will run only if successful'
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
               
}
}
