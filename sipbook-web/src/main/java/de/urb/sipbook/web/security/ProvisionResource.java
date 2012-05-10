/**
 * 
 */
package de.urb.sipbook.web.security;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.jboss.solder.logging.Logger;

/**
 * @author rainer
 *
 */
@Path("/provision")
public class ProvisionResource {

	@XmlRootElement(name="ProvisionAdminResult")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ProvisionAdminResult {
		private boolean provisioned;
		private boolean exists;

		public boolean isProvisioned() {
			return provisioned;
		}

		public void setProvisioned(boolean provisioned) {
			this.provisioned = provisioned;
		}

		public boolean isExists() {
			return exists;
		}

		public void setExists(boolean exists) {
			this.exists = exists;
		}
		
		
	}

	@Inject private Logger log;

	@GET
	@Path("/admin")
	@Produces("application/json")
	public ProvisionAdminResult provisionAdmin() {
		ProvisionAdminResult result = new ProvisionAdminResult();
		
		try {
		} catch(Exception e) {
			log.error("failed to provision admin", e);
		}
		
		return result;
	}
	
}
