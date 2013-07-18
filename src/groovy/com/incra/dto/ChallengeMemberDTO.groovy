package com.incra.dto

import com.incra.ChallengeMember

/**
 * The <i>ChallengeMemberDTO</i> class... 
 * 
 * @author Jeff Risberg
 * @since 02/26/11
 */
class ChallengeMemberDTO implements Comparable<ChallengeMemberDTO> {

  ChallengeMember member
  float total
  String name
  String teamName
  boolean isCaptain
  Date lastUpdated
  Long userId
  String customField1
  String customField2

  int compareTo(ChallengeMemberDTO t) {
    if (total == t.total) {
      // the sortedset will remove duplicates based on same total, which is not desireable
      return member.id.compareTo(t.member.id)
    }
    else {
      return t.total.compareTo(total)
    }
  }
}

