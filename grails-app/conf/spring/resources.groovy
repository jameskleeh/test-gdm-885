import throwaway.PasswordEncodingEventListener

// Place your Spring DSL code here
beans = {

    passwordEncodingEventListener(PasswordEncodingEventListener, ref('hibernateDatastore'))
}
