package com.incra.dto

import java.util.concurrent.CopyOnWriteArraySet

import org.bouncycastle.asn1.cmp.Challenge

import com.incra.ChallengeMember

/**
 * The <i>UserChallengeDTO</i> holds information that summarizes a challenge and its 
 * members/teams.  This is used for the leaderboard, for the challenge stats on the Game 
 * Plan screen, and other places.
 * 
 * @author Todd, Jeff
 * @since 01/06/11
 */
class ChallengeDTO {

  private Challenge challenge
  private SortedSet<ChallengeMemberDTO> members = new TreeSet()

  // MODIFICATION METHODS

  void addMember(ChallengeMemberDTO memberDTO) {
    members.add(memberDTO)
  }

  void removeMember(ChallengeMemberDTO memberDTO) {
    if (memberDTO)
      members.remove(memberDTO)
  }

  // ACCESS METHODS BEGIN HERE

  Challenge getChallenge() { return challenge }

  SortedSet<ChallengeMemberDTO> getMembers() { return members }

  int getMemberRank(ChallengeMember member) {
    if (member) {
      Long memberId = member.id

      CopyOnWriteArraySet cowasMembers = new CopyOnWriteArraySet(members)
      return cowasMembers.findIndexOf {it.member.id == memberId } + 1
    }
  }


  int getMemberTotal(ChallengeMember member) {
    if (member) {
      Long memberId = member.id

      CopyOnWriteArraySet cowasMembers = new CopyOnWriteArraySet(members)
      for (ChallengeMemberDTO memberDTO : cowasMembers) {
        if (memberDTO.member.id == memberId) {
          return memberDTO.total
        }
      }
    }
    return 0
  }

  public ChallengeMemberDTO getMemberDTO(ChallengeMember member) {
    if (member) {
      Long memberId = member.id

      CopyOnWriteArraySet cowasMembers = new CopyOnWriteArraySet(members)
      for (ChallengeMemberDTO memberDTO : cowasMembers) {
        if (memberDTO.member.id == memberId) {
          return memberDTO
        }
      }
    }
    return null
  }
}
