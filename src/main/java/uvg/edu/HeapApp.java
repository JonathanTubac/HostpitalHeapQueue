package uvg.edu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * HeapApp is a program that simulates a hospital queue system using a custom heap implementation.
 * It reads patient data from a file, stores the patients in a heap, and processes them in order of their priority.
 * 
 * The priority is determined by the 'prioridad' field of the Paciente class,
 * where 'A' has the highest priority, followed by 'B', and so on.
 */
public class HeapApp 
{
    /**
     * The main method of the application.
     * It reads patient data from a file, adds the patients to a heap,
     * and processes them in order of their priority.
     * 
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        vHeap<Paciente> cola = new vHeap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/uvg/edu/pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Split the line into parts: name, description, and priority
                String[] partes = linea.split(",");
                Paciente p = new Paciente(partes[0].trim(), partes[1].trim(), partes[2].trim().charAt(0));
                cola.add(p); // Add the patient to the heap
            }
        } catch (IOException e) {
            // Handle any IO exceptions that occur while reading the file
            e.printStackTrace();
        }

        // Print the order of attention based on priority
        System.out.println("Orden de atención:");
        while (!cola.isEmpty()) {
            System.out.println(cola.remove()); // Remove and print the patient with the highest priority
        }
    }
}