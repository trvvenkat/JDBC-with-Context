import com.mysql.jdbc.Connection;
import java.sql.*;
import java.sql.DriverManager;
/**
 * @desc A singleton database access class for MySQL
 * @author Ramindu
 */
public final class MysqlConnect {
    public Connection conn;
    private Statement statement;
    public static MysqlConnect db;
    private MysqlConnect() {
        String url= "jdbc:mysql://localhost:3306/";
        String dbName = "userdb";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "root";
        String password = "";
        try {
            Class.forName(driver).newInstance();
            this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
            System.out.println("Connection established");
        }
        catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }
    /**
     *
     * @return MysqlConnect Database connection object
     */
    public static synchronized MysqlConnect getDbCon() {
        if ( db == null ) {
            db = new MysqlConnect();
            System.out.println("Creating DB OBJ");
        }
        System.out.println("Returing DB OBJ");
        return db;
 
    }
    /**
     *
     * @param query String The query to be executed
     * @return a ResultSet object containing the results or null if not available
     * @throws SQLException
     */
    public ResultSet query(String query) throws SQLException{
        System.out.println("Before Executing query");
        statement = db.conn.createStatement();
        ResultSet res = statement.executeQuery(query);
        System.out.println("After Executing query");

        return res;
    }
    /**
     * @desc Method to insert data to a table
     * @param insertQuery String The Insert query
     * @return boolean
     * @throws SQLException
     */
    public int insert(String insertQuery) throws SQLException {
        statement = db.conn.createStatement();
        int result = statement.executeUpdate(insertQuery);
        return result;
 
    }
 
}