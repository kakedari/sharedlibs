#!/usr/bin/env groovy

def loadValuesYaml (Map buildParam) {
	def config = readYaml (file:"${buildParam?.configPath}")
	return config;
}

def call (Map buildParam) {
	def agentBox;
	def config;

	agentBox = "${buildParam?.node}"
		if (agentBox =='null') {
			agentBox = 'master'
		}
	print "Started Executing from node " + agentBox

pipeline {
	
	agent { node { label "${agentBox}" }}

	options {
		buildDiscarder(logRotator(artifactDaysToKeepStr: '',artifactNumToKeepStr: '', daysToKeepStr: '10', numToKeepStr: '10'))
		timeout time:20, unit: 'MINUTES'
	}                
	stages {
		stage ('Hello') {
			steps {
				echo "Hello World"
			}
		}
		stage ('Initialize & Load Variables') {
			steps {
				script{
					config = loadValuesYaml(buildParam)
					bat "echo 'Initialized and load variables successfully'"
					bat "echo 'This is my application name '" ${config.appName}
					bat "echo 'This is my artifactId %config.artifactId%'"
				}
			}
		}
		/**
		stage('Git Token') {
			environment {
				Git_token = credentials ('JENKINS-GIT-TOKEN')
			}
			steps {
				// bat "echo 'This is my first GIT TOKEN ${Git_token}'"
                		//bat "echo 'This is my Git Username %Git_token_USR%'"
                		bat "echo 'This is my Git Password %Git_token_PSW%'"
			}
		}**/
	}
   }
}
