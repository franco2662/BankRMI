import java.rmi.*;
import java.rmi.server.*;

class ServicioImp extends UnicastRemoteObject implements Servicio {
    ServicioImp() throws RemoteException {
    }
    public String eco(String s) throws RemoteException {
        return s.toUpperCase();
    }
}
