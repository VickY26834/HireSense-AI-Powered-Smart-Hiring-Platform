package hiresense.dbutils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class AppInitializer implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctxt=sce.getServletContext();
		//changes
		String dbUrl = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String appName = System.getenv("APP_NAME");
		DBConnection.openConnection(dbUrl, username, password);
		ctxt.setAttribute("appName", appName);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		DBConnection.closeConnection();
	}

}
