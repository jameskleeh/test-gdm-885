package throwaway

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import org.springframework.context.ConfigurableApplicationContext

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        GrailsApp.run(Application, args)
    }

    @Override
    void doWithApplicationContext() {
        if (applicationContext instanceof ConfigurableApplicationContext) {
            def listener = applicationContext.getBean(PasswordEncodingEventListener)
            applicationContext.addApplicationListener(listener)
        }
    }
}