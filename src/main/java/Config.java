import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.mysql.jdbc.Connection;

@WebListener
public class Config implements ServletContextListener {

    MysqlConnect db = MysqlConnect.getDbCon();
    Connection  c = db.conn;

    // @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext(); 
        servletContext.setInitParameter("username", "ABC");
        // db variable
        //db= MysqlConnect.getDbCon();
        //c = db.conn; 
    }

	public void contextDestroyed(ServletContextEvent sce) {
		
	}

    

}