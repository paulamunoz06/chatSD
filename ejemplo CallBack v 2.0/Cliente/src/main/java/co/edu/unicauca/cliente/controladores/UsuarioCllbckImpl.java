package co.edu.unicauca.cliente.controladores;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UsuarioCllbckImpl extends UnicastRemoteObject implements UsuarioCllbckInt
{
    
    public UsuarioCllbckImpl() throws RemoteException
    {
        super();		
    }
    //Muestra en la GUI
    @Override
    public void notificar(String mensaje, int cantidadUsuarios) throws RemoteException
    {
        System.out.println("Mensaje enviado del servidor: " + mensaje);       
        System.out.println("Cantidad de usuarios conectados: " + cantidadUsuarios );
    }

    @Override
    public void mostrarClientesRegistrados(String clientes) throws RemoteException {
        System.out.println("Clientes registrados en el servidor: \n" + clientes);
    }

    @Override
    public void ping() throws RemoteException {
        // Confirma que el cliente sigue accesible
    }

}
