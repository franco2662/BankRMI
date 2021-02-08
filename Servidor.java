import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.net.*;
import java.sql.*;


public class Servidor{
    private PostgreSQLJDBC con;

    public Servidor() {
        this.con = new PostgreSQLJDBC();
        
    }
    
    public static void main(String[] args) throws Exception {
        
        InetAddress ip;
        ip = InetAddress.getLocalHost();
        String localhost = "" + ip;
        System.setProperty("java.rmi.server.hostname",localhost);
        ServidorImp servidor = new ServidorImp();
        IServidor remote = (IServidor)UnicastRemoteObject.exportObject(servidor, 9900);
        LocateRegistry.createRegistry(0);
        Registry registry = LocateRegistry.getRegistry();
        registry.rebind("remoteServidor", remote);
        System.out.println("Servidor listo, Enter para terminar");
        System.in.read();
        registry.unbind("remoteServidor");
        UnicastRemoteObject.unexportObject(servidor, true);
         
     }

    
    
}