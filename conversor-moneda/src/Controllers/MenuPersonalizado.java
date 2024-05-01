package Controllers;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Scanner;

public class MenuPersonalizado {
    public void Personalizado(Divisas divisas, HistorialConsultas historial)
            throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la moneda base:");
        String monedaBase = scanner.nextLine();

        System.out.println("Ingrese el código objetivo:");
        String monedaObjetivo = scanner.nextLine();

        ConexionApi conexion = new ConexionApi(monedaBase, monedaObjetivo);
        HttpResponse<String> response = conexion.consulta(conexion.getDireccion());

        String json = response.body();

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES).create();
        Divisas conversion = gson.fromJson(json, Divisas.class);
        System.out.println(conversion);

        System.out.println("Ingrese el valor a convertir: ");
        Scanner valorUsuario = new Scanner(System.in);
        double valorAConvertir = valorUsuario.nextDouble();
        double resultado = conversion.getConversion() * valorAConvertir;
        System.out.println(valorAConvertir + " " + conversion.getMonedaBase() +
                " equivalen a: " + resultado + " " + conversion.getMonedaObjetivo());
        System.out.println();

        LocalDateTime marcaDeTiempo = LocalDateTime.now();

        String consultaPersonalizada = "Consulta personalizada: " + monedaBase + " a "
                + monedaObjetivo + " [" + marcaDeTiempo + "]";
        historial.agregarConsulta(consultaPersonalizada);
        String resultadoConversion = valorAConvertir + " " + conversion.getMonedaBase() + " equivalen a: "
                + resultado + " " + conversion.getMonedaObjetivo() + " [" + marcaDeTiempo + "]";
        historial.agregarConsulta(resultadoConversion);

        historial.guardarHistorialComoJSON();
    }
}
