package Controllers;

import java.io.IOException;
import java.util.Scanner;

public class MenuPrincipal {
    private Divisas divisas = new Divisas();
    private HistorialConsultas historial = new HistorialConsultas();
    private String menu = """
            Bienvenido/a al conversor de monedas.
            Por favor elija una de las siguientes opciones:
            1) Dolar a Peso colombiano
            2) Dolar a Peso Argentino
            3) Dolar a Real brasileño
            4) Euro a Peso colombiano
            5) Euro a Peso argentino
            6) Euro a Real brasileño
            7) Consulta personalizada
            8) Salir
            """;

    public void manejarOpcion(int opcion) throws IOException, InterruptedException {
        while (opcion != 8) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(menu);
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    String resultadoConsulta1 = divisas.obtenerDatosDivisas
                            ("usd", "cop", historial);
                    break;
                case 2:
                    String resultadoConsulta2 = divisas.obtenerDatosDivisas
                            ("usd", "ars", historial);
                    break;
                case 3:
                    String resultadoConsulta3 = divisas.obtenerDatosDivisas
                            ("usd", "brl", historial);
                    break;
                case 4:
                    String resultadoConsulta4 = divisas.obtenerDatosDivisas
                            ("eur", "cop", historial);
                    break;
                case 5:
                    String resultadoConsulta5 = divisas.obtenerDatosDivisas
                            ("eur", "ars", historial);
                    break;
                case 6:
                    String resultadoConsulta6 = divisas.obtenerDatosDivisas
                            ("eur", "brl", historial);
                    break;
                case 7:
                    MenuPersonalizado personalizado = new MenuPersonalizado();
                    personalizado.Personalizado(divisas, historial);
                    break;
                case 8:
                    System.out.println("Gracias por utilizar el conversor de monedas. Hasta luego.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor seleccione una opción válida.");
            }
        }
    }
}
