import java.sql.*;
import java.time.LocalDateTime;

public class PostgreSQLJDBC {
   protected Connection con;
   public PostgreSQLJDBC() {
      String jdbcURL = "jdbc:postgresql://localhost:5432/BankRMI";
      String username = "postgres";
      String password = "1234";
      try {
          this.con = DriverManager.getConnection(jdbcURL,username,password);
      } catch (Exception e) {
          System.out.println("Error");
          }
   }

  
  public void createUser(String document_id, String name, String username, String password) {
      try {
        String SQL = "INSERT INTO \"User\"(document_id, name, username, password) VALUES(?,?,?,?)";
        PreparedStatement pstmt = this.con.prepareStatement(SQL);
        pstmt.setString(1, document_id);
        pstmt.setString(2, name);
        pstmt.setString(3, username);
        pstmt.setString(4, password);
        pstmt.executeUpdate();

      } catch (Exception e) {
          System.out.println(e.getMessage());

         }
   }

   public int getNewAccount() {
      int numero_cuenta = 0;
      try {
          
          Statement stmt = null;
          stmt = this.con.createStatement();
          String query = "select  \"Account\".number from \"Account\" order by number desc limit 1";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
              if (rs.wasNull()) {
                 numero_cuenta = 100000000;
              }
              else{
                 numero_cuenta = rs.getInt("number") + 1;
              }
            
            }

      } catch (Exception e) {
          System.out.println(e.getMessage());

         }
      return numero_cuenta;
   }

   public void createAccount(String document_id) {
      int num_cuenta = getNewAccount();
      try {
          
        String SQL = "INSERT INTO \"Account\"(\"number\", current_balance, fk_document_id) VALUES (?, ?, ?)";
        PreparedStatement pstmt = this.con.prepareStatement(SQL);
        pstmt.setInt(1, num_cuenta);
        pstmt.setInt(2, 0);
        pstmt.setString(3, document_id);
        pstmt.executeUpdate();

      } catch (Exception e) {
          System.out.println(e.getMessage());

         }
   }

   public boolean verifyDocumentId(String document_id){
      boolean resultado = false;
      try {
          
         Statement stmt = null;
         stmt = this.con.createStatement();
         String query = "SELECT  \"User\".document_id FROM \"User\" where \"User\".document_id like " + document_id;
           ResultSet rs = stmt.executeQuery(query);

           while (rs.next()) {
             if (rs.wasNull()) {
                resultado = false;
             }
             else{
               resultado = true;
             }           
           }
           
     } catch (Exception e) {
         System.out.println(e.getMessage());
        }
        return resultado;   
   }

   public boolean countAccounts(String document_id){ // Si tiene 3 no puede crearse una cuenta nueva
      boolean resultado = false;
      try {
          
         Statement stmt = null;
         stmt = this.con.createStatement();
         String query = "SELECT COUNT( \"Account\".fk_document_id ) FROM \"Account\" where \"Account\".fk_document_id like " + document_id;
           ResultSet rs = stmt.executeQuery(query);

           while (rs.next()) {
             if (rs.getInt(1) < 3) {
                resultado = true;
             }
             else{
               resultado = false;
             }           
           }
           
     } catch (Exception e) {
         System.out.println(e.getMessage());
        }
        return resultado;   
   }

   

   public void susAmount(int account,int monto){
      try {

        String SQL = "UPDATE \"Account\" " + "SET current_balance = current_balance - ? " + "where \"Account\".\"number\" = ?";
         PreparedStatement pstmt = this.con.prepareStatement(SQL);
         pstmt.setInt(1, monto);
         pstmt.setInt(2, account);
         pstmt.executeUpdate();

           
           
     } catch (Exception e) {
         System.out.println(e.getMessage());
        }
   }

   
   public void addAmount(int cuenta,int amount){
        try {

         String SQL = "UPDATE \"Account\" " + "SET current_balance = current_balance + ? " + "where \"Account\".\"number\" = ?";
         PreparedStatement pstmt = this.con.prepareStatement(SQL);
         pstmt.setInt(1, amount);
         pstmt.setInt(2, cuenta);
         pstmt.executeUpdate();

           
           
     } catch (Exception e) {
         System.out.println(e.getMessage());
        }
   }

   public void makeTransaction(int cuenta_fuente,int cuenta_destino, int monto, String descripcion, int id_operation){ 
      
      try {
        // Date date= new Date();
        //  //getTime() returns current time in milliseconds
        // long time = date.getTime();
        //     //Passed the milliseconds to constructor of Timestamp class 
        // Timestamp fecha = new Timestamp(time);
         PreparedStatement pstmt = null;
         String query = "";
         if (id_operation==1){ //Deposito
          query = "INSERT INTO \"Transaction\"(amount, date, description, fk_account_source, fk_operation) VALUES (?, ?, ?, ?, ?)";
          pstmt = this.con.prepareStatement(query);
          pstmt.setInt(1,monto);
          pstmt.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now()));
          pstmt.setString(3,descripcion );
          pstmt.setInt(4,cuenta_fuente ); 
          pstmt.setInt(5,id_operation);
          pstmt.executeUpdate();
          addAmount(cuenta_fuente, monto);
         }

         if (id_operation==2){ //Retiro
          query = "INSERT INTO \"Transaction\"(amount, date, description, fk_account_source, fk_operation) VALUES (?, ?, ?, ?, ?)";
          pstmt = this.con.prepareStatement(query);
          pstmt.setInt(1,monto);
          pstmt.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now()));
          pstmt.setString(3,descripcion );
          pstmt.setInt(4,cuenta_fuente ); 
          pstmt.setInt(5,id_operation);
          pstmt.executeUpdate();
          susAmount(cuenta_fuente, monto);
          }
          if (id_operation==3){ //Transferencia
           query = "INSERT INTO \"Transaction\"(amount, date, description, fk_account_source, fk_account_destination, fk_operation) VALUES (?, ?, ?, ?, ?, ?)";
           pstmt = this.con.prepareStatement(query);
           pstmt.setInt(1,monto);
           pstmt.setTimestamp(2,Timestamp.valueOf(LocalDateTime.now()));
           pstmt.setString(3,descripcion );
           pstmt.setInt(4,cuenta_fuente ); 
           pstmt.setInt(5,cuenta_destino);
           pstmt.setInt(6,id_operation);
           pstmt.executeUpdate();
           susAmount(cuenta_fuente, monto);
           addAmount(cuenta_destino, monto);
          }

           
     } catch (Exception e) {
         System.out.println(e.getMessage());
        }

   }
  
}