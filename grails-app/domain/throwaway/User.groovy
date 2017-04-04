package throwaway

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.security.authentication.encoding.PasswordEncoder

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	static constraints = {
		password blank: false, password: true
		username blank: false, unique: true
	}

	static mapping = {
		password column: '`password`'
	}

	void encodePassword(PasswordEncoder passwordEncoder) {
		this.password = passwordEncoder.encodePassword(this.password, null)
    }
}
