#!/bin/groovy
pipeline {
    agent any
    stages {
          stage('SCM Checkout') {
          git 'https://del.tools.publicis.sapient.com/bitbucket/projects/BAET/repos/baet/browse/CodeQuality-MappingFile'
	  credentials('Lion@12345')
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
