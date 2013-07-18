package com.incra

import org.hibernate.Criteria
import org.hibernate.Query
import org.hibernate.Session
import org.hibernate.criterion.Projections
import org.hibernate.criterion.Restrictions

/**
 * The <i>UserService</i> provides business logic around Users, such as lookup, registration, 
 * and processing changes to the selected set of activities.
 *
 * @author Jeff Risberg
 * @since 09/20/10
 */
class UserService {

  static transactional = true

  def sessionFactory
  def springcacheService

  static final String CHARTDATA_CACHE_NAME = "chartDataCache"

  def getUser(params) {
    def user = User.findById(params.id)
    user
  }

  /**
   * Maintain login management information when logging in
   */
  def login(Object session, User user) {
    int loginCount = user.loginCount;

    user.loginCount = loginCount+1;
    user.lastLogin = new Date();

    session.user = user
  }

  /**
   * Maintain login management information when logging out
   */
  def logout(Object session) {
    session.user = null
  }

  /** 
   * Create the data set used on the home screen
   */
  def generateHome(User user) {
    // Perform a reporting query
    def session = sessionFactory.getCurrentSession();
    Criteria crit = session.createCriteria(UserActivity.class);

    crit.createAlias("activityType", "at", Criteria.LEFT_JOIN)
    crit.createAlias("at.activities", "a", Criteria.LEFT_JOIN)
    crit.createAlias("at.unitOfMeasure", "uom", Criteria.LEFT_JOIN)
    crit.add(Restrictions.eq("user", user));
    crit.add(Restrictions.eq("a.user", user));
    crit.setProjection(Projections.projectionList()
        .add(Projections.groupProperty("activityType"))
        .add(Projections.groupProperty("uom.name"))
        .add(Projections.count("a.activityType"))
        .add(Projections.sum("a.amount")));

    return crit.list()
  }

  /**
   * Create the data set used on the home screen
   */
  def generateChartData(User user) {
    println "generateChartData"
    springcacheService.doWithCache(CHARTDATA_CACHE_NAME, user.id, {
      // Perform a reporting query
      println "performing query"
      def session = sessionFactory.getCurrentSession();
      Criteria crit = session.createCriteria(UserActivityType.class);

      crit.createAlias("activityType", "at", Criteria.LEFT_JOIN)
      crit.createAlias("at.activities", "a", Criteria.LEFT_JOIN)
      crit.add(Restrictions.eq("user", user));
      crit.add(Restrictions.eq("a.user", user));
      crit.setProjection(Projections.projectionList()
          .add(Projections.groupProperty("activityType"))
          .add(Projections.count("a.activityType")));

      def result = crit.list()
      println result
      return result
    })
  }

  public resetChartDataCache(User user) {
    springcacheService.getOrCreateCache(CHARTDATA_CACHE_NAME).remove(user.id)
  }

  public flushChartDataCache() {
    springcacheService.getOrCreateCache(CHARTDATA_CACHE_NAME).flush()
  }

  /** 
   * Generate a list of the activities and their current status 
   */ 
  def generateActivities(User user) {
    println "fetching ActivityTypes"
    Session session = sessionFactory.getCurrentSession()
    Query query = session.createQuery("select activityType.id from UserActivityType where user = :user")
    query.setParameter("user", user)
    List<Long> selectedActivityTypeIds = query.list()

    List<Object[]> list = new ArrayList()
    List<Activity> activities = Activity.findAll()

    for (Activity at : activities) {
      Object[] tuple = new Object[2]
      tuple[0] = at
      tuple[1] = selectedActivityTypeIds.contains(at.id)
      list.add(tuple)
    }
    list
  }

  /**
   * Process the list of activities
   */
  def postActivities(User user, Object params) {

    List<Activity> activities = Activity.findAll()

    Session session = sessionFactory.getCurrentSession()
    Query query = session.createQuery("select activity.id from UserActivity where user = :user")
    query.setParameter("user", user)
    List<Long> selectedActivityIds = query.list()

    for (Activity at : activities) {
      Long atId = at.id;
      if (params.get("ActType_" + atId)) {
        if (activities.contains(atId) == false) {
          UserActivity ua = new UserActivity()
          ua.activity = at
          ua.effectivityStart = new Date()
          user.addToUserActivities(ua)
        }
      }
      else {
        if (selectedActivityIds.contains(atId) == true) {
          def uats = user.userActivities
          for (UserActivity ua : uats) {
            if (ua.activity.id.equals(atId)) {
              user.removeFromUserActivities(ua);
              break;
            }
          }
        }
      }
    }
  }
}
