import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Cliente {
    private static IServidor servidor = null;
    public static void main(String args[]) throws Exception {
       Registry registry = LocateRegistry.getRegistry();
       servidor = (IServidor) registry.lookup("remoteServidor");
       System.out.println(servidor.devolverMensaje());
        
    }
}