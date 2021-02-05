import java.rmi.*;

interface Servicio extends Remote {
        String eco (String s) throws RemoteException;
}