package co.edu.unicauca.cliente.utilidades;

import co.edu.unicauca.servidor.controladores.ControladorServidorChatInt;

public class UtilidadesMenu {
    public void mostrarMenu(String nickname, ControladorServidorChatInt servidor){
        System.out.println("==== Menu ====");
        System.out.println("1. Enviar Mensaje al chat grupal");
        System.out.println("2. Mostrar usuarios registrados en el chat grupal");
        System.out.println("3. Enviar Mensaje a un usuario");
        System.out.println("4. Salir");
        System.out.println("Seleccione una opción:");
        int opcion = UtilidadesConsola.leerEntero();

        switch(opcion){
            case 1:
                enviarMensajeChatGrupal(servidor);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4: 
                salirChatGrupal(nickname, servidor);
                break;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
                break;
        }
    }

    public void enviarMensajeChatGrupal(ControladorServidorChatInt servidor){
        try{
            System.out.println("Digite el mensaje a enviar al servidor: ");
            String mensaje=UtilidadesConsola.leerCadena();
            servidor.enviarMensaje(mensaje);
        }catch(Exception e){
            System.out.println("No se pudo ejecutar el metodo remoto enviarMensaje...");
            System.out.println(e.getMessage());
        } 
    }

    public void mostrarClientesRegitrados(ControladorServidorChatInt servidor){
        try{
            System.out.println("============Clientes Registrados============");
            servidor.mostrarClientesRegitrados(); //Ponerlo en el s y c
        }catch(Exception e){
            System.out.println("No se pudo ejecutar el metodo remoto mostrarClietntesRegistrados...");
            System.out.println(e.getMessage());
        } 
    }

    public void salirChatGrupal(String nickname, ControladorServidorChatInt servidor){
        try{
            System.out.println("Saliendo del chat grupal...");
            servidor.salir(nickname);
        }catch(Exception e){
            System.out.println("No se pudo ejecutar el metodo remoto enviarMensaje...");
            System.out.println(e.getMessage());
        } 
    }
}
