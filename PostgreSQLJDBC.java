import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreSQLJDBC {
   public static void main(String args[]) {
      Connection connection = null;
      String jdbcURL = "jdbc:postgresql://localhost:5432/BankRMI";
         String username = "postgres";
         String password = "1234";
      System.err.println("hola");
      try {
         //Class.forName("org.postgresql.Driver");
         connection = DriverManager.getConnection(jdbcURL,username,password);
         Statement stmt = null;
         stmt = connection.createStatement();
        
         ResultSet rs = stmt.executeQuery( "SELECT * FROM Operation" ); 

            while (rs.next()) {
                  System.out.print(rs.getString(1));
            }

         
      } catch (Exception e) {
         System.out.println(e.getMessage());
         System.out.println("fallo");
      }      
      
      
   }
}