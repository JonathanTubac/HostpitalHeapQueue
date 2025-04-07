package uvg.edu;
import java.io.*;
import java.util.*;

public class PriorityQueueApp {
    public static void main(String[] args) {
        PriorityQueue<Paciente> cola = new PriorityQueue<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/uvg/edu/pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                Paciente p = new Paciente(partes[0].trim(), partes[1].trim(), partes[2].trim().charAt(0));
                cola.add(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Orden de atención:");
        while (!cola.isEmpty()) {
            System.out.println(cola.poll());
        }
    }
}
