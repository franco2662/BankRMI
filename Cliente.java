import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {
    public static void main(String args[]){
        try {
            String saludo = "objetoHola";
            Registry registry = LocateRegistry.getRegistry();
            Hola h = (Hola) registry.lookup(saludo);
            System.out.println(h.saluda());
        }
        catch (RemoteException | NotBoundException e){
            System.err.println("Hubo un error en el cliente: ");
            e.printStackTrace();
        }
    }
}