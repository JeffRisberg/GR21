package com.incra

/**
 * The <i>ActivityEvent</i> domain class records one chunk of activity by a user.  In this version, 
 * all activities are recorded by a startDate and an amount.
 * 
 * @author Jeff Risberg
 * @since 09/19/10
 */
class ActivityEvent {

  Date startDate
  int amount
  String description
  Date dateCreated
  Date lastUpdated

  static constraints = {
    user()
    activity()
    startDate(nullable: false)
    amount(blank: false)
    description(blank: true)
    dateCreated(display: false)
  }

  static belongsTo = [ user : User, activity : Activity ]

  String toString() {
    "${user.profile.fullName} : ${activity.name} on ${startDate}"
  }
}
