#!/usr/bin/env groovy
import org.junit.*

@Test
def gitUtils_getProtocolForHTTPS_ReturnsHTTPS() {
    def dir = createRepo('https')
    dir("${dir}") {
        def protocol = getProtocol()
        assert "https" == protocol : "Expected https, got ${protocol}"
    }
}

@Test
def gitUtils_getProtocolForSSH_ReturnsSSH() {
    def dir = createRepo('ssh')
    dir("${dir}") {
        def protocol = getProtocol()
        assert "ssh" == protocol : "Expected ssh, got ${protocol}"
    }
}

@Test
def gitUtils_getProtocolForUnknown_ReturnsNull() {
    def dir = createRepo('garbage')
    dir("${dir}") {
        def protocol = getProtocol()
        assert Null == protocol : "Expected None, got ${protocol}"
    }
}

def createRepo(String protocol) {
    tmpdir = sh returnStdout: true, script: "mktemp --directory --tmpdir=${WORKSPACE}"
    if (tmpdir) {
        sh """
            cd ${WORKSPACE}/${tmpdir}
            git init
            git remote add origin ${protocol}://git@git/project/repo.git
        """
    } else {
        return Null
    }
    return tmpdir
}

return this
