package co.edu.unicauca.cliente.servicios;

import java.io.InputStream;
import java.util.Properties;

import co.edu.unicauca.cliente.controladores.UsuarioCllbckImpl;
import co.edu.unicauca.cliente.utilidades.UtilidadesConsola;
import co.edu.unicauca.cliente.utilidades.UtilidadesMenu;
import co.edu.unicauca.cliente.utilidades.UtilidadesRegistroC;
import co.edu.unicauca.servidor.controladores.ControladorServidorChatInt;

public class ClienteDeObjetos
{
    public static void main(String[] args)
    {
        try (InputStream input = ClienteDeObjetos.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("No se pudo encontrar el archivo config.properties");
                return;
            }
            Properties prop = new Properties();
            prop.load(input);

            String direccionIpRMIRegistry = prop.getProperty("server.ip");
            int numPuertoRMIRegistry = Integer.parseInt(prop.getProperty("server.port"));

            ControladorServidorChatInt servidor = (ControladorServidorChatInt) UtilidadesRegistroC.obtenerObjRemoto(numPuertoRMIRegistry, direccionIpRMIRegistry, "ServidorChat");
            UsuarioCllbckImpl objNuevoUsuario = new UsuarioCllbckImpl();

            String nickname;
            // El cliente se registra con un nickname que debe ser único.
            do {
                System.out.println("Digite el nickname con el que se va a identificar en el chat: ");
                nickname = UtilidadesConsola.leerCadena();
                if (servidor.registrarReferenciaUsuario(objNuevoUsuario, nickname)) {
                    System.out.println("Se ha registrado en el servidor de chat correctamente.");
                    break;
                } else {
                    System.out.println("El nickname ya está en uso.");
                }
            } while (true);

            UtilidadesMenu menu = new UtilidadesMenu();
            menu.mostrarMenu(nickname, servidor);

        } catch (Exception e) {
            System.out.println("No se pudo realizar la conexión con el servidor. Verifique que el servidor esté encendido.");
        }
    }
}

