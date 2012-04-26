/**
 * 
 */
package de.urb.timesheet.lib.faces.pages;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.InjectionTarget;
import javax.enterprise.util.AnnotationLiteral;

/**
 * @author rainer
 *
 */
public class PageResourcesExtension implements Extension {

	public static final String CONTEXT_ATTRIBUITE = "PAGE_RESOURCES_EL_NAME";
	
	private String variableName = "pageProps";
	
	
	public void installPerPageresourceBundle(@Observes AfterBeanDiscovery abd, BeanManager bm) {
		AnnotatedType<PerPageResourceBundle> at = bm.createAnnotatedType(PerPageResourceBundle.class);
		
		final InjectionTarget<PerPageResourceBundle> it = bm.createInjectionTarget(at);
		
		abd.addBean(new Bean<PerPageResourceBundle>() {

			@Override
			public PerPageResourceBundle create(CreationalContext<PerPageResourceBundle> ctx) {
				PerPageResourceBundle instance = it.produce(ctx);
				
				it.inject(instance, ctx);
				it.postConstruct(instance);
				
				return instance;
			}

			@Override
			public void destroy(PerPageResourceBundle instance, CreationalContext<PerPageResourceBundle> ctx) {
				it.preDestroy(instance);
				it.dispose(instance);
				
				ctx.release();
			}

			@Override
			public Set<Type> getTypes() {
				Set<Type> types = new HashSet<Type>();
				
				types.add(PerPageResourceBundle.class);
				types.add(Object.class);
				
				return types;
			}

			@Override
			public Set<Annotation> getQualifiers() {
				Set<Annotation> qualifiers = new HashSet<Annotation>();
				
				qualifiers.add(new AnnotationLiteral<Default>() {
					private static final long serialVersionUID = 5747816453306345599L;
					});
				qualifiers.add(new AnnotationLiteral<Any>() {
					private static final long serialVersionUID = -3484673772858132074L;
					});
				
				return qualifiers;
			}

			@Override
			public Class<? extends Annotation> getScope() {
				return ApplicationScoped.class;
			}

			@Override
			public String getName() {
				return variableName;
			}

			@Override
			public Set<Class<? extends Annotation>> getStereotypes() {
				return Collections.emptySet();
			}

			@Override
			public Class<?> getBeanClass() {
				return PerPageResourceBundle.class;
			}

			@Override
			public boolean isAlternative() {
				return false;
			}

			@Override
			public boolean isNullable() {
				return false;
			}

			@Override
			public Set<InjectionPoint> getInjectionPoints() {
				return it.getInjectionPoints();
			}
			
		});		
	}
}
