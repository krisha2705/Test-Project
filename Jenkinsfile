#!/bin/groovy
pipeline {
    agent any
    stages {
          
          stage('Compile-Stage') {
          //def mvnHome = tool name: 'apache-maven-3.6.0', type: 'maven'
          sh "mvn clean compile"
  }
          stage('Testing-Stage') {
          //def mvnHome = tool name: 'apache-maven-3.6.0', type: 'maven'
          sh "mvn test"
}
          stage('Deployment-Stage') {
          //def mvnHome = tool name: 'apache-maven-3.6.0', type: 'maven'
          sh "mvn deploy"
  }
 }
}
