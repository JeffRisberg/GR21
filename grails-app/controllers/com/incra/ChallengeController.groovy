package com.incra

import org.springframework.dao.DataIntegrityViolationException

class ChallengeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [challengeInstanceList: Challenge.list(params), challengeInstanceTotal: Challenge.count()]
    }

    def create() {
        [challengeInstance: new Challenge(params)]
    }

    def save() {
        def challengeInstance = new Challenge(params)
        if (!challengeInstance.save(flush: true)) {
            render(view: "create", model: [challengeInstance: challengeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'challenge.label', default: 'Challenge'), challengeInstance.id])
        redirect(action: "show", id: challengeInstance.id)
    }

    def show(Long id) {
        def challengeInstance = Challenge.get(id)
        if (!challengeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'challenge.label', default: 'Challenge'), id])
            redirect(action: "list")
            return
        }

        [challengeInstance: challengeInstance]
    }

    def edit(Long id) {
        def challengeInstance = Challenge.get(id)
        if (!challengeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'challenge.label', default: 'Challenge'), id])
            redirect(action: "list")
            return
        }

        [challengeInstance: challengeInstance]
    }

    def update(Long id, Long version) {
        def challengeInstance = Challenge.get(id)
        if (!challengeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'challenge.label', default: 'Challenge'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (challengeInstance.version > version) {
                challengeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'challenge.label', default: 'Challenge')] as Object[],
                          "Another user has updated this Challenge while you were editing")
                render(view: "edit", model: [challengeInstance: challengeInstance])
                return
            }
        }

        challengeInstance.properties = params

        if (!challengeInstance.save(flush: true)) {
            render(view: "edit", model: [challengeInstance: challengeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'challenge.label', default: 'Challenge'), challengeInstance.id])
        redirect(action: "show", id: challengeInstance.id)
    }

    def delete(Long id) {
        def challengeInstance = Challenge.get(id)
        if (!challengeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'challenge.label', default: 'Challenge'), id])
            redirect(action: "list")
            return
        }

        try {
            challengeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'challenge.label', default: 'Challenge'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'challenge.label', default: 'Challenge'), id])
            redirect(action: "show", id: id)
        }
    }
}
