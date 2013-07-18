package com.incra



import org.junit.*
import grails.test.mixin.*

@TestFor(ChallengeController)
@Mock(Challenge)
class ChallengeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/challenge/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.challengeInstanceList.size() == 0
        assert model.challengeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.challengeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.challengeInstance != null
        assert view == '/challenge/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/challenge/show/1'
        assert controller.flash.message != null
        assert Challenge.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/challenge/list'

        populateValidParams(params)
        def challenge = new Challenge(params)

        assert challenge.save() != null

        params.id = challenge.id

        def model = controller.show()

        assert model.challengeInstance == challenge
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/challenge/list'

        populateValidParams(params)
        def challenge = new Challenge(params)

        assert challenge.save() != null

        params.id = challenge.id

        def model = controller.edit()

        assert model.challengeInstance == challenge
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/challenge/list'

        response.reset()

        populateValidParams(params)
        def challenge = new Challenge(params)

        assert challenge.save() != null

        // test invalid parameters in update
        params.id = challenge.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/challenge/edit"
        assert model.challengeInstance != null

        challenge.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/challenge/show/$challenge.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        challenge.clearErrors()

        populateValidParams(params)
        params.id = challenge.id
        params.version = -1
        controller.update()

        assert view == "/challenge/edit"
        assert model.challengeInstance != null
        assert model.challengeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/challenge/list'

        response.reset()

        populateValidParams(params)
        def challenge = new Challenge(params)

        assert challenge.save() != null
        assert Challenge.count() == 1

        params.id = challenge.id

        controller.delete()

        assert Challenge.count() == 0
        assert Challenge.get(challenge.id) == null
        assert response.redirectedUrl == '/challenge/list'
    }
}
