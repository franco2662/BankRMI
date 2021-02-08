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
        
        System.out.println("Bienvenido");
        String valor = "\'26254452\'";
        int num = 5000;
        int acc = 100200300;
        int acc2 = 200300400;
        Servidor ser = new Servidor();
         
         
     }

    
    
}