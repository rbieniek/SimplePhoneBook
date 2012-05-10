/**
 * 
 */
package de.urb.sipbook.web.faces;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.faces.context.FacesContext;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

/**
 * @author rainer
 *
 */
public class PerPageResourceBundle extends ResourceBundle {

	private static final Logger logger = Logger.getLogger(PerPageResourceBundle.class);
	
	private Map<String, Map<Locale, Properties>> pageResourceBundles = new HashMap<String, Map<Locale,Properties>>();
	
	/* (non-Javadoc)
	 * @see java.util.ResourceBundle#getKeys()
	 */
	@Override
	public Enumeration<String> getKeys() {
		Vector<String> keys = new Vector<String>();
		
		for(Object o : findBundleForCurrentPage().keySet())
			keys.add(o.toString());
		
		return keys.elements();
	}

	/* (non-Javadoc)
	 * @see java.util.ResourceBundle#handleGetObject(java.lang.String)
	 */
	@Override
	protected Object handleGetObject(String key) {
		return findBundleForCurrentPage().getProperty(key);
	}

	private Properties findBundleForCurrentPage() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		String viewId = ctx.getViewRoot().getViewId();
		Locale locale = ctx.getExternalContext().getRequestLocale();
		
		if(!pageResourceBundles.containsKey(viewId))
			pageResourceBundles.put(viewId, new HashMap<Locale, Properties>());
		
		if(!pageResourceBundles.get(viewId).containsKey(locale)) {
			Properties props = new Properties();

			pageResourceBundles.get(viewId).put(locale, props);

			logger.infof("loading resources for view %s", viewId);
			
			String propFileName = viewId;
			
			if(StringUtils.startsWith(propFileName, "/"))
				propFileName = StringUtils.substring(propFileName, 1);
			
			int dotIdx = StringUtils.lastIndexOf(propFileName, ".");
			
			if(dotIdx > 0) {
				propFileName = StringUtils.substring(propFileName, 0, dotIdx);
				
				// try to load XML property file
				if(!loadXmlProperties(props, propFileName, locale.getCountry(), locale.getLanguage()))
					loadProperties(props, propFileName, locale.getCountry(), locale.getLanguage());
			}
		}
		
		return pageResourceBundles.get(viewId).get(locale);
	}
	
	private boolean loadXmlProperties(Properties props, String fileBaseName, String country, String language) {
		boolean loaded = false;
		InputStream is;
		String resourceName = fileBaseName  + "_" + language + "_" + country+ ".xml";
				
		logger.infof("loading resource: %s", resourceName);
		is = getClass().getClassLoader().getResourceAsStream(resourceName);
		if(is == null) {
			resourceName = fileBaseName + "_" + language + ".xml";
			
			logger.infof("loading resource: %s", resourceName);
			is = getClass().getClassLoader().getResourceAsStream(resourceName);

			if(is == null) {
				resourceName = fileBaseName + ".xml";
				
				logger.infof("loading resource: %s", resourceName);
				is = getClass().getClassLoader().getResourceAsStream(resourceName);
			}
		}

		if(is != null) {
			try {
				props.loadFromXML(is);
				loaded = true;
			} catch(Exception e) {
				logger.errorf(e, "failed to load properties for base name: %s", fileBaseName);
				
				throw new RuntimeException(e);
			}
		} else {
			logger.infof("failed to load XML properties for %s", fileBaseName);
		}
		return loaded;
	}
	
	private void loadProperties(Properties props, String fileBaseName, String country, String language) {
		InputStream is;
		String resourceName = fileBaseName + "_" + language + "_" + country + ".properties";
		
		logger.infof("loading resource: %s", resourceName);
		is = getClass().getClassLoader().getResourceAsStream(resourceName);
		if(is == null) {
			resourceName = fileBaseName + "_" + language + ".properties";
			
			logger.infof("loading resource: %s", resourceName);
			is = getClass().getClassLoader().getResourceAsStream(resourceName);

			if(is == null) {
				resourceName = fileBaseName + ".properties";
				
				logger.infof("loading resource: %s", resourceName);
				is = getClass().getClassLoader().getResourceAsStream(resourceName);
			}
		}

		if(is != null) {
			try {
				props.load(is);
			} catch(Exception e) {
				logger.errorf(e, "failed to loa properties for base name: %s", fileBaseName);
				
				throw new RuntimeException(e);
			}
		} else {
			logger.infof("failed to load XML properties for %s", fileBaseName);
		}
	}

}
