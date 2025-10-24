

package co.edu.unicauca.servidor.controladores;

import co.edu.unicauca.cliente.controladores.UsuarioCllbckInt;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ControladorServidorChatInt extends Remote
{
    public boolean registrarReferenciaUsuario(UsuarioCllbckInt  usuario, String nickname) throws RemoteException;
    public void enviarMensaje(String mensaje, String Nickname)throws RemoteException;
    public void salir(String nickname) throws RemoteException;
    public void mostrarClientesRegitrados() throws RemoteException;
}


