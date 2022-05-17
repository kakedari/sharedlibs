pipeline {
    agent any

    stages {
        stage('SCM Checkout') {
            steps {
                git 'https://github.com/kakedari/myapp-ansible.git'
            }
        }
        stage('Ansible') {
            steps {
                ansiblePlaybook credentialsId: 'Ansible', disableHostKeyChecking: true, installation: 'ansible', inventory: 'dev.inv', playbook: 'apache.yml'
            }
        }
    }
}
