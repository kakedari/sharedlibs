def add(x,y){
  echo "Sum of ${x} and ${y} is ${x+y}"
}  

def mul(x,y){
  echo "Multiplication of ${x} and ${y} is ${x*y}"
} 

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
