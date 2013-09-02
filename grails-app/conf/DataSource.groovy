dataSource {
  pooled = true
  driverClassName = "com.mysql.jdbc.Driver"
  username = "developer"
  password = "123456"
  url = "jdbc:mysql://localhost:3306/gr21?autoReconnect=true&zeroDateTimeBehavior=convertToNull"
}

hibernate {
  cache.use_second_level_cache = true
  cache.use_query_cache = false
  cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
  connection.zeroDateTimeBehavior = 'convertToNull'
}

environments {
  development {
    dataSource {
      // one of 'create', 'create-drop', 'update'
      dbCreate = "update"
      url = "jdbc:mysql://localhost:3306/gr21?autoReconnect=true&zeroDateTimeBehavior=convertToNull"
      driverClassName = "com.mysql.jdbc.Driver"
      // logSql = true
    }
  }
  test {
    dataSource {
      dbCreate = "update"
      url = "jdbc:h2:tempDB"
      driverClassName = "org.h2.Driver"
      username = "sa"
      password = ""
    }
  }
  production {
    dataSource {
      dbCreate = "update"
      url = "jdbc:mysql://localhost:3306/gr21?autoReconnect=true&zeroDateTimeBehavior=convertToNull"
      driverClassName = "com.mysql.jdbc.Driver"
    }
  }
}
