package co.edu.unicauca.cliente.servicios;

import co.edu.unicauca.cliente.controladores.UsuarioCllbckImpl;
import co.edu.unicauca.cliente.utilidades.UtilidadesConsola;
import co.edu.unicauca.cliente.utilidades.UtilidadesMenu;
import co.edu.unicauca.cliente.utilidades.UtilidadesRegistroC;
import co.edu.unicauca.servidor.controladores.ControladorServidorChatInt;

public class ClienteDeObjetos
{
    public static void main(String[] args)
    {
        try
        {
            ControladorServidorChatInt servidor;
            int numPuertoRMIRegistry = 0;
            String direccionIpRMIRegistry = "";
            System.out.println("Cual es el la dirección ip donde se encuentra  el rmiregistry ");
            direccionIpRMIRegistry = UtilidadesConsola.leerCadena();
            System.out.println("Cual es el número de puerto por el cual escucha el rmiregistry ");
            numPuertoRMIRegistry = UtilidadesConsola.leerEntero();
            servidor = (ControladorServidorChatInt) UtilidadesRegistroC.obtenerObjRemoto(numPuertoRMIRegistry,direccionIpRMIRegistry, "ServidorChat");
            UsuarioCllbckImpl objNuevoUsuario= new UsuarioCllbckImpl();
            
            // Registrar el usuario en el servidor
            boolean bandera = true;
            String nickname;
            do{
                System.out.println("Digite el nickname con el que se va a identificar en el chat: ");
                nickname = UtilidadesConsola.leerCadena(); 
                if (servidor.registrarReferenciaUsuario(objNuevoUsuario, nickname)){
                    System.out.println("Se ha registrado el usuario en el servidor de chat correctamente.");
                    bandera = true;
                } else {
                    System.out.println("El nickname ya está en uso. Registre otro nickname.");
                    bandera = false;
                }
            }while(!bandera);

            // Mostrar el menú de opciones para el cliente registrado
            UtilidadesMenu menu = new UtilidadesMenu();
            menu.mostrarMenu(nickname,servidor);
        }
        catch(Exception e){
            System.out.println("No se pudo realizar la conexion...");
            System.out.println(e.getMessage());
        }

    }
}

