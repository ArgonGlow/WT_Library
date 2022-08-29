package libSec;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import libSec.SecurityConfig; 

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SecurityConfig.class };
	} 
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	} 
	
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}