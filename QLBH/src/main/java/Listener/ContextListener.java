package Listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextListener() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	ServletContext context = sce.getServletContext();
    	String log4jconfigFile = context.getInitParameter("log4j-config-location");
    	String fullpath = context.getRealPath("/") + File.separator + log4jconfigFile;
    	PropertyConfigurator.configure(fullpath);
    }
	
}
