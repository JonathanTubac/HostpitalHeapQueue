/*Programmed by Jonathan Tubac 24484 */
package uvg.edu;

/**
 * The Paciente class represents a patient in the hospital queue system.
 * Each patient has a name, a symptom description, and a priority level.
 * 
 * The priority level determines the order in which patients are attended to,
 * where 'A' has the highest priority, followed by 'B', and so on.
 */
public class Paciente implements Comparable<Paciente> {
    private String nombre;
    private String sintoma;
    private char prioridad;

    /**
     * Constructs a new Paciente with the specified name, symptom, and priority.
     * 
     * @param nombre    The name of the patient.
     * @param sintoma   A description of the patient's symptoms.
     * @param prioridad The priority level of the patient ('A' is the highest priority).
     */
    public Paciente(String nombre, String sintoma, char prioridad) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.prioridad = prioridad;
    }

    /**
     * Gets the name of the patient.
     * 
     * @return The name of the patient.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Gets the symptom description of the patient.
     * 
     * @return The symptom description of the patient.
     */
    public String getSintoma() {
        return sintoma;
    }

    /**
     * Gets the priority level of the patient.
     * 
     * @return The priority level of the patient.
     */
    public char getPrioridad() {
        return prioridad;
    }

    /**
     * Compares this patient to another patient based on their priority level.
     * 
     * @param otro The other patient to compare to.
     * @return A negative integer, zero, or a positive integer as this patient's
     *         priority is less than, equal to, or greater than the other patient's priority.
     */
    @Override
    public int compareTo(Paciente otro) {
        return Character.compare(this.prioridad, otro.prioridad);
    }

    /**
     * Returns a string representation of the patient, including their name,
     * symptom description, and priority level.
     * 
     * @return A string representation of the patient.
     */
    @Override
    public String toString() {
        return nombre + " - " + sintoma + " (Prioridad: " + prioridad + ")";
    }
}