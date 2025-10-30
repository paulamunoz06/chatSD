

package co.edu.unicauca.servidor.controladores;

import co.edu.unicauca.cliente.controladores.UsuarioCllbckInt;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ControladorServidorChatInt extends Remote
{
    public boolean registrarReferenciaUsuario(UsuarioCllbckInt  usuario, String nickname) throws RemoteException;
    public void enviarMensaje(String mensaje)throws RemoteException;
    public void salir(String nickname) throws RemoteException;
    public void enviarMensajeAUsuario(String mensaje, String nicknameOrigen, String nicknameDestino) throws RemoteException;
    public void clientesRegistrados(String nickname) throws RemoteException;
    public List<String> obtenerNicknames() throws RemoteException;
    public boolean estaConectado(String nickname) throws RemoteException;
}


