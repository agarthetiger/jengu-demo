#!/usr/bin/env groovy
library identifier: "jengu@master",
    retriever: modernSCM([
        $class: 'GitSCMSource',
        remote: 'https://github.com/agarthetiger/jengu.git'
    ])

def libraryVersion = (!env.BRANCH_NAME || env.BRANCH_NAME.startsWith('PR')) ?
    'master' : env.BRANCH_NAME
library identifier: "jengu-demo@${libraryVersion}",
    retriever: modernSCM([
        $class: 'GitSCMSource',
        remote: 'https://github.com/agarthetiger/jengu-demo.git'
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
