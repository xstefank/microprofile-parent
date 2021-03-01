pipeline {
    agent any
    parameters {
        string(description: 'The path to be removed', name: 'removePath')
    }

    stages {
        stage("Remove Artifact From Eclipse Downloads") {
            steps {
                sshagent ( ['projects-storage.eclipse.org-bot-ssh']) {
                    sh "ssh genie.microprofile@projects-storage.eclipse.org [ -e /home/data/httpd/download.eclipse.org/microprofile/${params.removePath} ] || (echo 'The requested path ${params.removePath} not found in microprofile/ directory' && exit 1)"
                    sh "ssh genie.microprofile@projects-storage.eclipse.org rm -rf /home/data/httpd/download.eclipse.org/microprofile/${params.removePath}"
                }
            }
        }
    }
}
