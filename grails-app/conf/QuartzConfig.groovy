quartz {
  autoStartup = false
  jdbcStore = false
}

environments {
  development {
    quartz { autoStartup = true }
  }
  test {
    quartz { autoStartup = false }
  }
  production {
    quartz { autoStartup = true }
  }
}