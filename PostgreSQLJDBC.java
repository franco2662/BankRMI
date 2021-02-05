import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQLJDBC {
   public static void main(String args[]) {
      Connection c = null;
      String jdbcURL = "jdbc:postgresql://localhost:5432/BankRMI";
         String username = "postgres";
         String password = "1234";
      try {
         c = DriverManager
            .getConnection(jdbcURL,username,password);
      } catch (Exception e) {
        System.out.println("Error conectando a la base de datos");
         e.printStackTrace();
         
      }
      
   }
}