
package co.edu.unicauca.servidor.servicios;

import co.edu.unicauca.servidor.controladores.ControladorServidorChatImpl;
import co.edu.unicauca.servidor.utilidades.UtilidadesConsola;
import co.edu.unicauca.servidor.utilidades.UtilidadesRegistroS;

import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.util.Properties;

public class ServidorDeObjetos
{
    public static void main(String args[]) throws RemoteException
    {        
    
        try (InputStream input = ServidorDeObjetos.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();
            prop.load(input);

            String direccionIpRMIRegistry = prop.getProperty("server.ip");
            int numPuertoRMIRegistry = Integer.parseInt(prop.getProperty("server.port"));

            System.out.println("Cargando configuración desde config.properties...");
            System.out.println("Dirección IP: " + direccionIpRMIRegistry);
            System.out.println("Puerto: " + numPuertoRMIRegistry);

            ControladorServidorChatImpl objRemoto = new ControladorServidorChatImpl();

            UtilidadesRegistroS.arrancarNS(numPuertoRMIRegistry);
            UtilidadesRegistroS.RegistrarObjetoRemoto(objRemoto, direccionIpRMIRegistry, numPuertoRMIRegistry, "ServidorChat");

        } catch (IOException e) {
            System.err.println("Ocurrió un error al leer el archivo de propiedades.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("No fue posible Arrancar el NS o Registrar el objeto remoto: " + e.getMessage());
        }
        
    }
}
