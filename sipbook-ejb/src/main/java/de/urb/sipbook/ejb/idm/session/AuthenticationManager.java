/**
 * 
 */
package de.urb.sipbook.ejb.idm.session;

import javax.ejb.Local;

import de.urb.sipbook.ejb.idm.model.IdentityUser;

/**
 * @author rainer
 *
 */
@Local
public interface AuthenticationManager {

	public IdentityUser authenticate(String name, String password);
}
