#!/bin/groovy
def mvnHome
pipeline {
    agent any
    stages {
	stage('Initialize the variable') {
 steps {
	script {
	mvnHome = tool name: 'apache-maven-3.6.0', type: 'maven'
	}
    }
}
          stage('SCM Checkout') {
  steps {
          echo "Pulling code from repo...."
          git branch: 'master', credentialsId: '64c944c0-c872-476c-a81a-37178b9c8487', url: 'https://github.com/krisha2705/Test-Project/' 
        }
}
          stage('Compile-Stage') {
   steps {
          echo "Compiling Code"       
          sh "${mvnHome}/bin/mvn clean compile"
         }
}
          stage('Testing-Stage') {
   steps {
	  echo "Testing Code"
          sh "${mvnHome}/bin/mvn test"
         }
}
          stage('Deployment-Stage') {
    steps {
	  echo "Deploying project"
          sh "${mvnHome}/bin/mvn deploy"
   }
  }
 }
}
