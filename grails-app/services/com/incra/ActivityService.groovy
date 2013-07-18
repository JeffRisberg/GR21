package com.incra

class ActivityException extends RuntimeException {
	String message
	ActivityEvent activity
}

/**
 * The <i>ActivityService</i> provides business logic around Activities, such as finding all Activities
 * in a range, or of a type, or creating a new Activity
 * 
 * @author Jeff Risberg
 * @since 09/20/10
 */
class ActivityService {

	boolean transactional = true

	def int MAX_ENTRIES_PER_PAGE = 10

	def getActivities(User user) {
		user.activities
	}

	/**
	 * Create a new activity record
	 */
	ActivityEvent createActivity(User user, Object params) {
		def activityType = Activity.findById(params.activityTypeId)

		def activity = new ActivityEvent()
		activity.user = session.user
		activity.activityType = activityType
		activity.amount = params.amount as Integer
		activity.startDate = params.when

		if (activity.save()) {
			return activity
		}
		else {
			return null
		}
	}

	/**
	 * to be filled in
	 */
	def getGlobalTimelineAndCount(params) {

		if (!params.max)
			params.max = MAX_ENTRIES_PER_PAGE

		def posts = ActivityEvent.list(params)
		def postCount = ActivityEvent.count()
		[posts, postCount]
	}

	/**
	 * candidate for caching
	 */
	def getUserTimelineAndCount(userId, params) {
		if (!params.max)
			params.max = MAX_ENTRIES_PER_PAGE

		if (!params.offset)
			params.offset = 0

		def user = User.findByUserId(userId)
		def idsToInclude = user.following.collect { u -> u.id }
		idsToInclude.add(user.id)
		def query = "from Activity as p where p.user.id in (" + idsToInclude.join(",") + ")"
		println "Query is ${query}"
		def posts = ActivityEvent.findAll( query + " order by p.dateCreated desc",
				[ max: params.max, offset: params.offset ])
		def postCount = ActivityEvent.findAll(query).size() // TODO use count criteria
		println "Post count is ${posts?.size()}"
		return [posts, postCount]}

	/**
	 * Candidate for caching
	 */
	def getUserActivities(userId, params) {
		if (!params.max)
			params.max = MAX_ENTRIES_PER_PAGE

		def user = User.findByUserId(userId)
		def activityCount = ActivityEvent.countByUser(user)
		def activities = ActivityEvent.findAllByUser(user, params)
		return [activities, activityCount]
	}
}
