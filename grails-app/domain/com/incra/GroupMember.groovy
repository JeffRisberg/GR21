package com.incra


/**
 * The <i>GroupMember</i> domain class...
 * 
 * @author Jeff Risberg
 * @since 09/19/10
 */
class GroupMember {

  User user
  UserGroup userGroup

  static constraints = {
  }

  String toString() {
    ${user}
  }
}
