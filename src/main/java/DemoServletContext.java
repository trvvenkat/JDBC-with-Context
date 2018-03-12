import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/example", initParams = {
        @WebInitParam(name = "email", value = "webmaster@domain.com", description = "Email from webmaster"),
        @WebInitParam(name = "phone", value = "xxxx/xx.xx.xx", description = "Phone webmaster") })

public class DemoServletContext extends HttpServlet {

    private String email, phone;
    private Statement statement = null;
    private MysqlConnect mysqlConnect;
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        ServletContext servletContext = getServletContext();

        ServletConfig config = getServletConfig();

        email = config.getInitParameter("email");
        phone = config.getInitParameter("phone");

        out.write("<h2>Servlet 3 initialisation parameter annotation example: @WebInitParam</h2>");
        out.write("<p><strong>E-mail: </strong>" + email + "</p>");
        out.write("<p><strong>Phone: </strong>" + phone + "</p>");
        out.write("<p><strong>Bookname: </strong>" + servletContext.getInitParameter("username") + "</p>");
        Config cc = new Config();
            
        try {
        ResultSet rs = cc.db.query("select * from employee");
        while(rs.next())
        {
            out.print(rs.getString(1)+"  "+rs.getString(2)+ " \n");
        }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

   @Override
   public void init() throws ServletException {
       super.init();

   }
   public ResultSet query(String query) throws SQLException{
    Config cc = new Config();
    System.out.println("Before Executing query");
    //MysqlConnect db= MysqlConnect.getDbCon();
    //Connection c = db.conn;
    statement = cc.c.createStatement();
    ResultSet res = statement.executeQuery(query);
    System.out.println("After Executing query");

    return res;
}
}