package Controllers;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Divisas {
    @SerializedName("base_code")
    private String monedaBase;
    @SerializedName("target_code")
    private String monedaObjetivo;
    @SerializedName("conversion_rate")
    private double conversion;
    private HistorialConsultas historial = new HistorialConsultas();


    public String obtenerDatosDivisas(String monedaBase, String monedaObjetivo, HistorialConsultas historial)
            throws IOException, InterruptedException {
        ConexionApi conexion = new ConexionApi(monedaBase, monedaObjetivo);
        HttpResponse<String> response = conexion.consulta(conexion.getDireccion());
        String json = response.body();

        Gson gson = new GsonBuilder().setFieldNamingPolicy
                (FieldNamingPolicy.UPPER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();
        Divisas datosDivisas = gson.fromJson(json, Divisas.class);
        this.conversion = datosDivisas.getConversion();
        System.out.println(datosDivisas.toString());

        System.out.println("Ingrese el valor a convertir: ");
        Scanner valorUsuario = new Scanner(System.in);
        double valorAConvertir = valorUsuario.nextDouble();
        double resultado = this.conversion * valorAConvertir;
        System.out.println(valorAConvertir + " " + datosDivisas.getMonedaBase() +
                " equivalen a: " + resultado + " " + datosDivisas.getMonedaObjetivo());
        System.out.println();

        LocalDateTime marcaDeTiempo = LocalDateTime.now();

        String consulta = "Consulta: " + monedaBase + " a " + monedaObjetivo + " [" + marcaDeTiempo + "]";
        String resultadoConsulta = "Resultado: " + valorAConvertir + " " + datosDivisas.getMonedaBase() +
                " equivalen a: " + resultado + " " + datosDivisas.getMonedaObjetivo() + " [" + marcaDeTiempo + "]";
        historial.agregarConsulta(consulta);
        historial.agregarConsulta(resultadoConsulta);
        historial.guardarHistorialComoJSON();
        return datosDivisas.toString();
    }

    public double getConversion() {
        return conversion;
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public String getMonedaObjetivo() {
        return monedaObjetivo;
    }

    @Override
    public String toString() {
        return "El " + monedaBase +
                ", frente al " + monedaObjetivo +
                ", equivale a: " + conversion + " " + monedaObjetivo;
    }
}