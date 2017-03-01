package throwaway

import grails.plugin.springsecurity.SpringSecurityService
import groovy.transform.CompileStatic
import groovy.transform.InheritConstructors
import org.grails.datastore.mapping.engine.event.AbstractPersistenceEvent
import org.grails.datastore.mapping.engine.event.AbstractPersistenceEventListener
import org.grails.datastore.mapping.engine.event.EventType
import org.grails.datastore.mapping.engine.event.PreInsertEvent
import org.grails.datastore.mapping.engine.event.PreUpdateEvent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEvent

@InheritConstructors
@CompileStatic
class PasswordEncodingEventListener extends AbstractPersistenceEventListener {

    @Autowired
    SpringSecurityService springSecurityService

    @Override
    protected void onPersistenceEvent(AbstractPersistenceEvent event) {
        if (event.entityObject instanceof User) {
            println("${event.eventType.name()} captured for User")
            User user = (User)event.entityObject
            if (user.password) {
                if (event.eventType == EventType.PreInsert || user.isDirty('password')) {
                    println "Event is preinsert or password is dirty, encoding password"
                    user.password = springSecurityService.encodePassword(user.password)
                }
            }
        }
    }

    @Override
    boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        eventType == PreInsertEvent || eventType == PreUpdateEvent
    }
}
