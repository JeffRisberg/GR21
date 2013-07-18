package com.incra

import java.util.Date;

/**
 * The <i>ChallengeMember</i> domain class...
 * 
 * @author Jeff Risberg
 * @since 09/19/10
 */
class ChallengeMember {
	
  Challenge challenge
	User user
	Date effectivityStart
	Date effectivityEnd
	
	static constraints = {
		user(nullable: false)		
	}
	
	String toString() {
		if (user) {
			"${user.profile.fullName} : ${user.name}"
		} else {
			"no user : ${user.name}"
		}
	}
}
