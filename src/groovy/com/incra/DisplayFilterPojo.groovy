package com.incra


/**
 * The <i>DisplayFilterPojo</i> describes a filter element of a display, and is used by the 
 * filter rendering tag to paint the filters
 * 
 * @author Jeffrey Risberg
 * @since 10/22/10
 */
class DisplayFilterPojo {
  String name
  String label
  String type
  def values
}
