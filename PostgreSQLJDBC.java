import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQLJDBC {
   public static void main(String args[]) {
      Connection connection = null;
      String jdbcURL = "jdbc:postgresql://localhost:5432/BankRMI";
         String username = "postgres";
         String password = "1234";
      System.err.println("hola");
      try {
         connection = DriverManager.getConnection(jdbcURL,username,password);
         
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }      
      
      
   }
}