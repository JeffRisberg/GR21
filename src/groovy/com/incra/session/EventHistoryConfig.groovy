package com.incra.session

/**
 * The <i>EventHistoryConfig</i> is a session-level object that contains the 
 * configuration to be used for Event-oriented History displays, such as the 
 * viewing of Event, Reminders, and Stats.  The configuration information 
 * specifies the rangeOption, and the flags for showing events and showing 
 * reminders.  This object is passed to many stat-fetching routines.
 *
 * @author Jeffrey Risberg
 * @since late July 2011
 */
class EventHistoryConfig {
  String rangeOption
  Boolean showEvents
  Boolean showReminders

  EventHistoryConfig() {
    this.rangeOption = "Month"
    this.showEvents = true
    this.showReminders = true
  }
}
