#!/bin/groovy
pipeline {
    agent any
    stages {
          stage('SCM Checkout') {
          git 'https://github.com/krisha2705/Test-Project.git
}
          stage('Compile-Stage') {
          //def mvnHome = tool name: 'apache-maven-3.6.0', type: 'maven'
          bat "mvn clean compile"
  }
          stage('Testing-Stage') {
          //def mvnHome = tool name: 'apache-maven-3.6.0', type: 'maven'
          bat "mvn test"
}
          stage('Deployment-Stage') {
          //def mvnHome = tool name: 'apache-maven-3.6.0', type: 'maven'
          bat "mvn deploy"
  }
 }
}
