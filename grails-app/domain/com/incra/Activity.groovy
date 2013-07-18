package com.incra

import com.incra.domain.nonentity.ActivityCategory

/**
 * The <i>Activity</i> domain class defines an activity, such as "Treadmill", or "Bicycle".
 * 
 * @author Jeff Risberg
 * @since 09/19/10
 */
class Activity {

  String name
  ActivityCategory activityCategory
  UnitOfMeasure unitOfMeasure
  Date dateCreated
  Date lastUpdated

  static constraints = {
    name(blank: false, unique: true)
    dateCreated(display: false)
  }

  static belongsTo = [ activityCategory : ActivityCategory ]

  static hasMany = [ activities : ActivityEvent ]

  String toString() {
    "${name}"
  }
}
