/**
 * 
 */
package de.urb.sipbook.web.security;


import javax.ejb.EJB;
import javax.inject.Inject;

import org.jboss.seam.security.Authenticator;
import org.jboss.seam.security.BaseAuthenticator;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;
import org.jboss.solder.logging.Logger;
import org.picketlink.idm.impl.api.PasswordCredential;
import org.picketlink.idm.impl.api.model.SimpleUser;

import de.urb.sipbook.ejb.idm.model.IdentityUser;
import de.urb.sipbook.ejb.idm.session.AuthenticationManager;

/**
 * @author rainer
 *
 */
public class SipbookAuthenticator extends BaseAuthenticator implements	Authenticator {
	@Inject private Credentials credentials;
	@Inject private Identity identity;
	@Inject private Logger log;
	@EJB private AuthenticationManager am;

	/* (non-Javadoc)
	 * @see org.jboss.seam.security.Authenticator#authenticate()
	 */
	@Override
	public void authenticate() {
		log.infof("authenticating user='%s'", credentials.getUsername());

		setStatus(AuthenticationStatus.FAILURE);

		IdentityUser user = am.authenticate(credentials.getUsername(), ((PasswordCredential)credentials.getCredential()).getValue());
		
		if(user != null) {

			setUser(new SimpleUser(user.getUuid()));
			setStatus(AuthenticationStatus.SUCCESS);
		}
	}

}
