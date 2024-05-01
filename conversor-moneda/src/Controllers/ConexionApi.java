package Controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexionApi {
    private String codigoBase;
    private String codigoObjetivo;
    private String direccion;

    public String getDireccion() {
        this.direccion = direccion = "https://v6.exchangerate-api.com/v6/37afc1f652572574dc406fb2/pair/"
                + codigoBase + "/" + codigoObjetivo;
        return direccion;
    }

    public ConexionApi(String codigoBase, String codigoObjetivo) {
        this.codigoBase = codigoBase;
        this.codigoObjetivo = codigoObjetivo;
    }

    public HttpResponse<String> consulta (String direccion) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

         return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
