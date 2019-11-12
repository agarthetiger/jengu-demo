#!/usr/bin/env groovy
library identifier: "jengu@v0.1.0",
    retriever: modernSCM([
        $class: 'GitSCMSource',
        remote: 'git@github.com:agarthetiger/jenkins-shared-library.git'
    ])

def libraryVersion = (!env.BRANCH_NAME || env.BRANCH_NAME.startsWith('PR')) ?
    'master' : env.BRANCH_NAME
library identifier: "jengu-demo@${libraryVersion}",
    retriever: modernSCM([
        $class: 'GitSCMSource',
        remote: 'git@github.com:agarthetiger/jengu-demo.git'
    ])

pipeline {
    agent any
    options {
        ansiColor('xterm')
        timestamps()
    }
    stages {
        stage('Run Tests'){
            steps {
                libraryTestRunner()
            }
        }
    }
}
