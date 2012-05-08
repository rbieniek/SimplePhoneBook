/**
 * 
 */
package de.urb.sipbook.web.faces;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;
import org.jboss.solder.logging.Logger;

/**
 * @author rainer
 *
 */
@Named("validatorMessage")
@RequestScoped
public class ValidatorMessageHelper {

	@Inject Logger logger;
	
	public boolean hasMessageFor(String componentId) {
		boolean result = false;
		FacesContext ctx = FacesContext.getCurrentInstance();

		try {
			if(StringUtils.isNotBlank(componentId)) {
				result = !ctx.getMessageList(componentId).isEmpty();
			}
		} catch(Exception e) {
			logger.errorf(e, "failed read messages for component: {0}", componentId);
		}
		
		return result;
	}
}
