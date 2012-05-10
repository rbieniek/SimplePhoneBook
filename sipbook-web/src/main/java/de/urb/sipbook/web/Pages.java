/**
 * 
 */
package de.urb.sipbook.web;

import org.jboss.seam.faces.rewrite.FacesRedirect;
import org.jboss.seam.faces.security.AccessDeniedView;
import org.jboss.seam.faces.security.LoginView;
import org.jboss.seam.faces.view.config.ViewConfig;
import org.jboss.seam.faces.view.config.ViewPattern;

/**
 * @author rainer
 *
 */
@ViewConfig
public interface Pages {

	static enum InnerPages {
		
		@FacesRedirect
		@ViewPattern("/*")
		@AccessDeniedView("/security/denied.xhtml")
		@LoginView("/security/login.xhtml")
		ALL;
	}
}
