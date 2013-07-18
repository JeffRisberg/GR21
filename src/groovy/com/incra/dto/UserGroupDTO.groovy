package com.incra.dto

import com.incra.UserGroup


/**
 * The <i>UserGroupDTO</i> holds the information needed to draw the group structure screen.
 * 
 * @author Jeff Risberg
 * @since 02/16/11
 */
class UserGroupDTO {
  Long id
  String name
  String description
  String path
  int level
  UserGroupDTO parent
  List<UserGroupDTO> children

  /** Constructor */
  public UserGroupDTO(UserGroup userGroup) {
    this.id = userGroup.id
    this.name = userGroup.groupName
    this.description = userGroup.description
    this.path = userGroup.groupPath
    this.level = 0;
    this.parent = null
    this.children = new ArrayList<UserGroupDTO>()
  }

  /** Constructor */
  public UserGroupDTO(Long id, String name, String description, String path) {
    this.id = id
    this.name = name
    this.description = description
    this.path = path
    this.level = 0;
    this.parent = null
    this.children = new ArrayList<UserGroupDTO>()
  }

  /** Add a new child */
  public void addChild(UserGroupDTO newChild) {
    newChild.parent = this
    this.children.add(newChild)
  }

  public String toString ( ) {
    path
  }
}
