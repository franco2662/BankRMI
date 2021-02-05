import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor{
    public static void main(String args[]){
        try {
            String saludo = "objetoHola";
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(saludo, new ImpleHola());
            System.out.println("Servidor Hola funcionando");
        }
        catch (RemoteException e){
            System.err.println("Hubo un error en el servidor: ");
            e.printStackTrace();
        }
    }
}