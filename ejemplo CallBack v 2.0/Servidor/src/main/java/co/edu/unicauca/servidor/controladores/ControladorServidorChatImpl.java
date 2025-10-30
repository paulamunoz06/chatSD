package co.edu.unicauca.servidor.controladores;


import co.edu.unicauca.cliente.controladores.UsuarioCllbckInt;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ControladorServidorChatImpl extends UnicastRemoteObject implements ControladorServidorChatInt {

    private final Map<String, UsuarioCllbckInt> usuarios;//lista que almacena la referencia remota de los clientes y su nickname

    public ControladorServidorChatImpl() throws RemoteException
    {
        super();
        usuarios= new HashMap<>();
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> verificarUsuariosActivos(), 10, 10, TimeUnit.SECONDS);
    }
    
    @Override
    public synchronized boolean registrarReferenciaUsuario(UsuarioCllbckInt usuario, String nickname) throws RemoteException 
    {
       //método que unicamente puede ser accedido por un hilo 
        System.out.println("Invocando al método registrar usuario desde el servidor");
        boolean bandera=false;
        if (!usuarios.containsKey(nickname)) {
            usuarios.put(nickname, usuario);
            bandera = true;
        }
        return bandera;       
    }
   
    @Override
    public void enviarMensaje(String mensaje)throws RemoteException 
    {        
        notificarUsuarios("Un usuario envio el siguiente mensaje: " + mensaje);
    }

    @Override
    public void mostrarClientesRegitrados() throws RemoteException
    {
        //Imprime los nicknames (llaves en el hasmap) de los usarios
        this.usuarios.forEach((llave,valor)->{
            int i = 0;
            System.out.println("Usuario " + i +": + " + llave);
            i++;
        }
        );
    }

    @Override
    public void salir(String nickname) throws RemoteException 
    {
        //Método para que un usuario salga del chat
        System.out.println("El usuario ha salido del chat");
        usuarios.remove(nickname);
        notificarUsuarios("El usuario " + nickname + " ha salido del chat.");
    }

    private void notificarUsuarios(String mensaje) throws RemoteException 
    {
        System.out.println("Invocando al método notificar usuarios desde el servidor");
        for (UsuarioCllbckInt objUsuario : usuarios.values()) {
            objUsuario.notificar(mensaje, usuarios.size());     
        }

    }

    public void enviarMensajeAUsuario(String mensaje, String nicknameOrigen, String nicknameDestino) throws RemoteException {
        UsuarioCllbckInt usuarioDestino = usuarios.get(nicknameDestino);
        if (usuarioDestino != null) {
            usuarioDestino.notificar("Mensaje de " + nicknameOrigen + ": " + mensaje, usuarios.size());
        } else {
            UsuarioCllbckInt usuarioOrigen = usuarios.get(nicknameOrigen);
            if (usuarioOrigen != null) {
                usuarioOrigen.notificar("El usuario " + nicknameDestino + " no está conectado.", usuarios.size());
            }
        }
    }


    public List<String> obtenerNicknames() throws RemoteException {
        return new ArrayList<>(usuarios.keySet());
    }   

    public boolean estaConectado(String nickname) throws RemoteException {
        return usuarios.containsKey(nickname);
    }

    private void verificarUsuariosActivos() {
        usuarios.forEach((nickname, usuario) -> {
            try {
                usuario.ping();
            } catch (RemoteException e) {
                System.out.println("El usuario " + nickname + " se ha desconectado abruptamente.");
                usuarios.remove(nickname);
                
            }
        });
    }

    @Override
    public void mostrarClientesRegistrados() throws RemoteException {
        System.out.println("Clientes registrados:");
        int i = 0;
        for (String key : usuarios.keySet()) {
            System.out.println("Usuario " + i + ": " + key);
            i++;
        }
    }
}
