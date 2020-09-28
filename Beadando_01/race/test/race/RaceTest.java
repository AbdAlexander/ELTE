package race;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class RaceTest {
    /**
     * Test of main method, of class Race.
     */
    @Test
    public void testMain() {
    }

    /**
     * Itt tesztelem a menu() és a checkChoice() függvényeket egyszerre.
     */
    @Test
    public void testCheckChoice() {
        int choice0 = 0; //Kilépés a programból
        int choice1 = 1; //Tetszőleges fájl futtatása
        int choice2 = 2; //Példa tesztfájl futtatása
        int choice3 = 3; //Tesztek futtatása
        int choice4 = 4; //Hibás bementi érték
        int choice5 = -1; //Hibás bementi érték
        assertEquals(choice0, Race.menu(choice0)); // Ha létezik ilyen menüpont, visszaadja annak az értékét.
        assertEquals(choice1, Race.menu(choice1)); // Ha létezik ilyen menüpont, visszaadja annak az értékét.
        assertEquals(choice2, Race.menu(choice2)); // Ha létezik ilyen menüpont, visszaadja annak az értékét.
        assertEquals(choice3, Race.menu(choice3)); // Ha létezik ilyen menüpont, visszaadja annak az értékét.
        assertTrue(choice4 != Race.menu(choice4)); // Mivel nem létezik 4-es menüpont, ennek az értéknek true-nak kell lennie.
        assertTrue(choice5 != Race.menu(choice5)); // Mivel nem létezik -1-es menüpont, ennek az értéknek true-nak kell lennie.
    }

    /**
     * Test of choices method, of class Race.
     */
    @Test
    public void testChoices() {
    }

    /**
     * Test of checkInputFile method, of class Race.
     */
    @Test
    public void testCheckInputFile() throws Exception {
    }

    /**
     * Test of startSimulation method, of class Race.
     */
    @Test
    public void testStartSimulation() {
    }

    /**
     * Test of simulationAnalyze method, of class Race.
     */
    @Test
    public void testSimulationAnalyze() {
    }
    
}
