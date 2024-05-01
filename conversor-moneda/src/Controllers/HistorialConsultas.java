package Controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistorialConsultas {
    private List<String> consultas = new ArrayList<>();

    public void agregarConsulta(String consulta) {
        consultas.add(consulta);
    }

    public void guardarHistorialComoJSON() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);

        try (FileWriter file = new FileWriter("historial.json")) {
            file.write(json);
        } catch (IOException e) {
            System.out.println("Error al guardar el historial de consultas: " + e.getMessage());
        }
    }
}