package uvg.edu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testHeapOrder() {
        vHeap<Paciente> heap = new vHeap<>();

        // Add patients to the heap
        heap.add(new Paciente("Maria", "Apendicitis", 'A'));
        heap.add(new Paciente("Juan", "Fractura", 'C'));
        heap.add(new Paciente("Carmen", "Dolores de parto", 'B'));

        // Verify the order of removal (based on priority)
        assertEquals("Maria - Apendicitis (Prioridad: A)", heap.remove().toString());
        assertEquals("Carmen - Dolores de parto (Prioridad: B)", heap.remove().toString());
        assertEquals("Juan - Fractura (Prioridad: C)", heap.remove().toString());

        // Verify the heap is empty
        assertTrue(heap.isEmpty());
    }
}
