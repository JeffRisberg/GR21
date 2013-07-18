package com.incra

/**
 * The <i>ActivityEventController</i> class...
 * 
 * @author Jeffrey Risberg
 * @since 09/19/10
 */
class ActivityEventController {

  def userService
  def activityService
  def scaffold = true

  static navigation = [
    [group: 'tabs', action: 'timeline', title: 'My Timeline', order: 1],
    [action: 'global', title: 'Everyone', order: 2],
  ]

  def index = { redirect(action: list) }

  def ajaxGetUomName = {
    def activityType = Activity.get(params.id)
    render activityType?.unitOfMeasure.name
  }

  def global = {
    def activities = ActivityEvent.findAll()
    [ activities : activities, activityCount : activities.size() ]
  }

  def timeline = {
    if (session.user) {
      def user = User.findById(session.user.id)

      [ user : user, profile : user.profile ]
    } else {
      flash.message = "Invalid user id"
      redirect(controller : 'user', action : list)
    }
  }
}
