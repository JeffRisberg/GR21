package com.incra

import com.incra.domain.nonentity.ChallengeStatusType

/**
 * The <i>Challenge</i> domain class has a name, a status, and a list of activities.
 * 
 * @author Jeff Risberg
 * @since 09/19/10
 */
class Challenge {

  String name
  String description
  ChallengeStatusType statusType
  Date startDate
  Date endDate
  Date dateCreated
  Date lastUpdated

  static constraints = {
    name(blank: false, unique: true)
    description()
    statusType()
    startDate()
    endDate()
    dateCreated(display: false)
    lastUpdated(display: false)
  }

  static hasMany = [ activities : ActivityEvent ]

  String toString() {
    "${name}"
  }
}
