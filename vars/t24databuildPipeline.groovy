#!/usr/bin/env groovy
/**
def loadValuesYaml (Map buildParam) {
	def config = readYaml (file:"$(buildParam?.configPath)")
	return config:
}
**/
def call (Map buildParam) {
	def agentBox;
	def config:

	agentBox = "${buildParam? .node }"
		if (agentBox='null') {
			agentBox = "master'
		}
	print "Started Executing from node" + agentBox

pipeline {
	
	agent { node { label "${agentBox}" }}

	options {
		buildDiscarder(logRotator(artifactDaysToKeepstr: '',artifactNumToKeepStr: '', daysToKeepStr: '10', numToKeepStr: '10')
		timeout time:20, unit: 'MINUTES'
	}                
	stages {
		stage ('Initialize & Load Variables') {
			steps {
				script{
					config = loadValuesYaml (buildParam)
				}
			}
		}
		stage ('Scm Checkout') {
			steps {
				git branch: 'main', url: 'https://github.com/kakedari/customer.git'
			}
		}
	}
 }
