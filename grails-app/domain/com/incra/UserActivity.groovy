package com.incra

/**
 * The <i>UserActivity</i> domain class keeps track of what activities a user has signed up for, and when.
 * 
 * @author Jeff Risberg
 * @since 09/19/10
 */
class UserActivity {

  Activity activity
  Date effectivityStart
  Date effectivityEnd

  static constraints = {
    user(nullable: true)
    activity()
    effectivityStart()
    effectivityEnd(nullable: true)
  }

  static belongsTo = [ user : User ]

  String toString() {
    if (user) {
      "${user.profile.fullName} : ${activityType.name}"
    } else {
      "no user : ${activityType.name}"
    }
  }
}
