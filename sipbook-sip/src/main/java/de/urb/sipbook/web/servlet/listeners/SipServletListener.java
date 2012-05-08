/**
 * 
 */
package de.urb.sipbook.web.servlet.listeners;

import javax.servlet.ServletContext;

import org.jboss.weld.environment.servlet.deployment.URLScanner;
import org.mobicents.servlet.sip.ctf.core.environment.servlet.SipServletsListener;

import de.urb.sipbook.web.servlet.deployment.VFS3URLScanner;

/**
 * @author rainer
 *
 */
public class SipServletListener extends SipServletsListener {

	@Override
	protected URLScanner createUrlScanner(ClassLoader classLoader, ServletContext context) {
		try
		{
			classLoader.loadClass("org.jboss.vfs.VFS"); // check if we can use JBoss VFS
			return new VFS3URLScanner(classLoader);
		}
		catch (Throwable t)
		{
			return super.createUrlScanner(classLoader, context);
		}
	}

}
