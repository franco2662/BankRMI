import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Servidor{
    private PostgreSQLJDBC con;

    public Servidor() {
        this.con = new PostgreSQLJDBC();
        
    }
    
    public static void main(String args[]){
        
        System.out.println("hola");
        String valor = "\'26254452\'";
        Servidor ser = new Servidor();
        boolean a = ser.con.countAccounts(valor);
        System.out.println(ts); 
         
     }

    
    
}