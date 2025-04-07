/*Programmed by Jonathan Tubac 24484 */
package uvg.edu;
import java.io.*;
import java.util.*;

/**
 * PriorityQueueApp is a program that simulates a hospital queue system.
 * It reads patient data from a file, stores the patients in a priority queue,
 * and processes them in order of their priority.
 * 
 * The priority is determined by the 'prioridad' field of the Paciente class,
 * where 'A' has the highest priority, followed by 'B', and so on.
 */
public class PriorityQueueApp {

    /**
     * The main method of the application.
     * It reads patient data from a file, adds the patients to a priority queue,
     * and processes them in order of their priority.
     * 
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        PriorityQueue<Paciente> cola = new PriorityQueue<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/uvg/edu/pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Split the line into parts: name, description, and priority
                String[] partes = linea.split(",");
                Paciente p = new Paciente(partes[0].trim(), partes[1].trim(), partes[2].trim().charAt(0));
                cola.add(p); // Add the patient to the priority queue
            }
        } catch (IOException e) {
            // Handle any IO exceptions that occur while reading the file
            e.printStackTrace();
        }

        // Print the order of attention based on priority
        System.out.println("Orden de atención:");
        while (!cola.isEmpty()) {
            System.out.println(cola.poll()); // Remove and print the patient with the highest priority
        }
    }
}