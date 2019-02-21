#!/bin/groovy
#Defining global envirnment variable below
pipeline {
    agent any
    stages {

          stage('SCM Checkout') {
  steps {
          script {
          echo "Pulling code from repo...."
          git branch: 'master', credentialsId: 'b943f49a-7aec-4b06-adb4-815ce4abe3a2', url: 'https://del.tools.publicis.sapient.com/bitbucket/projects/BAET/repos/baet/browse/CodeQuality-MappingFile' 
        }
     }
}
          stage('Compile-Stage') {
   steps {
          script {
          echo "Compiling Code"       
          bat '"C:\apache-maven-3.6.0\bin\mvn clean compile"'
         }
     }
}
          stage('Testing-Stage') {
   steps {
	  script {
	  echo "Testing Code"
          bat '"C:\apache-maven-3.6.0\bin\mvn clean compile"'
         }
      }
}
          stage('Deployment-Stage') {
    steps {
           script {
	  echo "Deploying project"
          bat '"C:\apache-maven-3.6.0\bin\mvn clean compile"'
}
   }
  }
 }
}
