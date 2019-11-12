#!/usr/bin/env groovy
import org.junit.*

@Test
def log_Info_MsgPresentInConsole() {
    def infoMsg = "An INFO message."
    log.info infoMsg
    def buildLog = currentBuild.rawBuild.getLog(99).join('\n')
    assert buildLog.contains(infoMsg) : "Could not find info message in build log."
}

@Test
def log_DebugWithoutDebugFlag_MsgAbsentFromConsole() {
    def debugMsg = "A DEBUG message"
    log.debug debugMsg
    def buildLog = currentBuild.rawBuild.getLog(99).join('\n')
    assert !buildLog.contains(debugMsg) : "Found debug message in build log which should not have been present."

}

@Test
def log_DebugWithDebugFlagSet_MsgPresentInConsole() {
    def debugMsg = "A DEBUG message"
    withEnv(['DEBUG=true']) {
        log.debug debugMsg
    }
    buildLog = currentBuild.rawBuild.getLog(99).join('\n')
    assert buildLog.contains(debugMsg) : "Could not find debug message in build log."
}

return this
