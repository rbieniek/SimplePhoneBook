/**
 * 
 */
package de.urb.sipbook.ejb.idm.session;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import de.urb.sipbook.ejb.idm.model.IdentityUser;

/**
 * @author rainer
 *
 */
@Stateless
@Local(AuthenticationManager.class)
public class AuthenticationManagerBean implements AuthenticationManager {

	private @PersistenceContext EntityManager em;
	Logger logger = Logger.getLogger(AuthenticationManagerBean.class);
	
	/* (non-Javadoc)
	 * @see de.urb.sipbook.ejb.idm.session.Authenticator#authenticate(java.lang.String, java.lang.String)
	 */
	@Override
	public IdentityUser authenticate(String name, String password) {
		IdentityUser user = null;

		logger.infof("authenticating user: %s", name);
		
		if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(password)) {
			try {
				user = em.createNamedQuery(IdentityUser.QUERY_FIND_BY_NAME, IdentityUser.class).setParameter(IdentityUser.QUERY_FIND_BY_NAME_PARAM1, name).getSingleResult();
			} catch(Exception e) {
				logger.errorf(e, "failed to authenticate user: %s", name);
			}
		}
		
		return user;
	}

}
