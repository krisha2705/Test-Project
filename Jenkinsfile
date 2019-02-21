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
          "${mvnHome}\mvn clean compile"
         }
}
          stage('Testing-Stage') {
   steps {
	  echo "Testing Code"
          "${mvnHome}\mvn test"
         }
}
          stage('Deployment-Stage') {
    steps {
	  echo "Deploying project"
          "${mvnHome}\mvn deploy"
   }
  }
 }
}
