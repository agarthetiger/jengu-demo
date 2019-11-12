#!/usr/bin/env groovy

/* Get the protocol used by git (push) for the current directory */
def getProtocol() {
  def gitRemote = sh encoding: 'utf-8', label: '', returnStdout: true, script: 'git remote -v'
  def matcher
  for (String line in gitRemote.readLines()) {
    matcher = line =~ /^origin[\s]+([^:]+).*\(push\)$/
    if (matcher.find()) {
      break
    }
  }
  return matcher?.size() > 0 ? matcher[0][1] : Null
}
