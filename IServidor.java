import java.rmi.*;

public interface IServidor extends Remote {
    public String devolverMensaje() throws RemoteException;
}
