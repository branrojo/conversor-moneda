import Controllers.MenuPrincipal;

import java.io.IOException;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args){
       try {
           int opcion = 0;
           MenuPrincipal menuPrincipal = new MenuPrincipal();
           menuPrincipal.manejarOpcion(opcion);
       } catch (IOException | InterruptedException | InputMismatchException e) {
           System.out.println("Error al consultar las divisas: " + e.getMessage());
       }
    }
}

