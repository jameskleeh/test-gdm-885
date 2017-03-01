package throwaway

class BootStrap {

    def init = { servletContext ->
        new User(username: "admin", password: "admin").save(flush: true)
    }
    def destroy = {
    }
}
