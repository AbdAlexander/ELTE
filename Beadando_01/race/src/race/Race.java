package race;

import java.io.File;  
import java.io.FileNotFoundException;  
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner; 

public class Race {
    static int daysGoneBy = 0; // Ezt az adattagot változtatom a különböző eljárások és metódusoknál, és a kiértékeléskor is fel kell használjam, ezért inicializáltam statikusan és globálisan.
    
    public static void main(String[] args) {
        int choice = menu(); //menu() függvény egy int-et ad vissza, ami a menüben választott menüpontunk.
        try{
            checkChoice(choice);  //Ellenőrizzük, hogy a felkínált lehetőségek közül választottunk-e.
        }
        catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
        String result = choices(choice); //Resultba eltároljuk a menüből választott menüpontot, majd utána kiértékeljük.
        switch (result) {
            case "quiting":
                System.out.println("Kilépés a programból.");
                return;
            case "unkown_error":
                return;
            case "start_testing":
                System.out.println("Tesztek indításához a Test Packeges-ből kell elindítani a megfelelő jUnit tesztet!");
                System.out.println("Sajnos nem tudtam megoldani, hogyan indíthatom a főprogramból a jUnit teszteket :(");
                return;
            default: //Fálj név megadva VAGY Example.txt
                try {
                    checkInputFile(result); //Lekérdezzük, hogy a megadott file megfelel-e a kritériumoknak. Ha nem, exception dob!
                    ArrayList<Creatures> creatures = new ArrayList<>();
                    File input = new File(result);
                    Scanner reader = new Scanner(input);
                    int creatures_db = reader.nextInt();
                    
                    System.out.println("------ Bemeneti adatok ------");
                    for(int i=0;i<creatures_db;i++) {
                        String name = reader.next();
                        String type = reader.next();
                        int water = reader.nextInt(); reader.nextLine();
                        System.out.println(name + " " + type + " " + water);
                        
                        switch (type) {
                            case "h":
                                creatures.add(new Homokjaro(name,water));
                                break;
                            case "s":
                                creatures.add(new Szivacs(name,water));
                                break;
                            case "l":
                                creatures.add(new Lepegeto(name,water));
                                break;
                            default:
                                break;
                        }
                    }
                    String days = reader.nextLine();
                    reader.close();
                    System.out.println(days);
                    System.out.println("------------------------------");
                    
                    Scanner in = new Scanner(System.in);
                    System.out.print("Részletezzem a szimulációt? (I/N): ");
                    String detailsString = in.nextLine();
                    boolean details;
                    switch (detailsString) {
                        case "I":
                        case "i":
                            details = true;
                            System.out.println("Részletezés bekapcsolva!");
                            break;
                        case "N":
                        case "n":
                            details = false;
                            System.out.println("Részletezés kikapcsolva!");
                            break;
                        default:
                            System.err.println("Hibás bemenet! Kilépés...");
                            return;
                    }
                    simulationAnalyze(startSimulation(creatures,days,details)); //Elindul a szimuláció, a szimuláció visszatérési értéke lesz a szimuláció kiértékelő függvénynek a paramétere
                }
                catch (FileNotFoundException | IllegalArgumentException | InputMismatchException e) {
                    System.err.println(e.getMessage());
                }   break;
        } 
    }
    /**
     * menu() statikus függvény kiírja a menüpontokat.
     * @throws InputMismatchException, ha a választás nem egy szám! 
     * @return Visszaadja az értéket, amit a felhasználó választott
     */
    public static int menu() throws InputMismatchException  {
        Scanner in = new Scanner(System.in); 
        
        System.out.println("------Lények versenye------");
        System.out.println("Kérlek válassz az alábbi lehetőségek közül");
        System.out.println("1. Tetszőleges tesztfájl futtatása");
        System.out.println("2. Példa tesztfájl futtatása");
        System.out.println("3. Tesztek futtatása (Fejlesztés alatt)");
        System.out.println("0. Kilépés");
        System.out.print("Választásod: "); 
        try {
            int choice = in.nextInt();
            in.nextLine();
            return choice;
        }
        catch (InputMismatchException e) {
            return -1;
        }
    }
    /**
     * jUnit tesztekhez létrehozott menü metódus, hogy lehessen tesztelni a menü működését.
     * @param testChoice Scanner megfelelője, így lehet majd szimulálni a felhasználó választását.
     * @return választás eredménye
     * @throws InputMismatchException Ha a választás típushibás
     */
    public static int menu(int testChoice) throws InputMismatchException {
        try {
            int choice = testChoice;
            return choice;
        }
        catch (InputMismatchException e) {
            return -1;
        }
    } 
    /**
     * Az alábbi eljárás ellenőrzi a menu()-ből kapott értéket.
     * @param choice a menü()-ből szerzett választás értéke 
     * @throws IllegalArgumentException, ha bemeneti érték nincs menüben.
     */
    public static void checkChoice(int choice) throws IllegalArgumentException {
         if(choice<0 || choice>3) 
            throw new IllegalArgumentException("Hibás bementi érték!");
    }
    /**
     * Az alábbi függvény kiértékeli a menu()-ből kapott értéket, majd elküldi a főprogramnak, hogy mit kezdjen vele.
     * @param choice menu()-ből kapott választás értéke
     * @return Érték lehet egy input file neve, vagy egy általam készített érték, melyet a főprogramban kezelek le.
     */
    public static String choices(int choice) {
        switch(choice) {
            case 0: //Kilépés
                return "quiting";
            case 1: //Tesztfájlból olvasás
                Scanner in = new Scanner(System.in); 
                System.out.print("Kérem az inputfájl nevét: "); 
                return in.nextLine();
            case 2: //Példa futtatása
                return "example.txt";
            case 3: //Tesztek futtatása
                return "start_testing";    
            //Defaultot nem ellenőrzöm, mivel erre már van megfelelő try-catch ág.
        }
        return "unkown_error";
    }
    /**
     * checkInputFile eljárás ellenőrzi, hogy a beadott input fáljban minden adat megfelelően szerepel-e.
     * @param fileName A fájl neve, amit a felhasználó megadott, hogy olvassunk.
     * @throws IllegalArgumentException Amennyiben olyan érték szerepel a fáljban, amit nem tud kezelni a program
     * @throws FileNotFoundException Ha megadott file nem található
     * @throws InputMismatchException Ha olyan érték szerepel a fájlban, ami típusában hibás (pl int helyett String szerepel)
     */
    public static void checkInputFile(String fileName) throws IllegalArgumentException, FileNotFoundException, InputMismatchException  {
        File input = new File(fileName);
        Scanner reader = new Scanner(input);
        if(!reader.hasNextInt()) {
            throw new InputMismatchException("HIBA: A lények száma csak Integer típusú szám lehet!");
        }
        int creatures_db = reader.nextInt();
        if(creatures_db < 1) {
            throw new IllegalArgumentException("HIBA: Lények száma kevesebb, mint 1.");
        }
        for(int i=0;i<creatures_db;i++) {
            String name = reader.next();
            if(name.length()<1) {
                throw new IllegalArgumentException("HIBA: Lény neve rövidebb, mint 1 hosszú.");
            }
            String type = reader.next();
            if(!(type.equals("h") || type.equals("s") || type.equals("l") )) {
                throw new IllegalArgumentException("HIBA: Lény típusa hibás! Elfogadott típusok: h,s,l. Beadott típus: " + type);
            }
            int water = reader.nextInt(); reader.nextLine();
            if(water < 0) {
                throw new IllegalArgumentException("HIBA: Lény kezdeti vízmennyisége nem lehet negatív.");
            }
        }
        String days = reader.nextLine(); 
        
        if(days.length()<=0) {
            throw new IllegalArgumentException("HIBA: Nincsenek megadva a napok.");
        }
        for(int i=0;i<days.length();i++) {
            if(!(days.charAt(i) == 'n' || days.charAt(i) == 'f' || days.charAt(i) == 'e')) {
                throw new IllegalArgumentException("HIBA: Hibás naptípus. Elfogadott típusok: n,f,e. Beadott típus: "+ days.charAt(i));
            }
        }
        reader.close(); 
    }
    /**
     * startSimulation függvény a legfontosabb része a programnak, mely elindítja a szimulációt,
     * mely addig tart, amíg nem érünk a napok végére, vagy egy lény marad életben.
     * @param creatures megkapjuk a lények gyűjteményét, mellyel dolgozunk a szimulációban.
     * @param days megkapjuk, hogy hány nap van, és hogy milyen napok következnek
     * @param details ha true, akkor részletezzük a szimuláció részleteit a konzolban, ha false, akkor nem.
     * @return Szimuláció végén visszadjuk a megkapott lények gyűjteményét, a már módosított értékekkel, melyet majd szimulációt analizáló eljárás fog kiérékelni
     */
    public static ArrayList<Creatures> startSimulation(ArrayList<Creatures> creatures, String days, boolean details) {
        if(details) { // Van részletezés
            int aliveCreatures=0;
            for(int i=0;i<days.length() && aliveCreatures!=1 ;i++) {
                switch (days.charAt(i)) {
                    case 'n': // Napos idő következik
                        System.out.println("------ " + (i+1) + ". nap: NAPOS -----");
                        creatures.stream().map(c -> {
                            c.sunny();
                            return c;
                        }).forEachOrdered(c -> {
                            System.out.println(c.getName() + ": összesen " + c.getTravelled()
                                + " egységet tett meg eddig. (Víztartalék: "
                                + c.getWater() + ")." );
                        });
                        System.out.println("------ " + (i+1) + ". nap: Véget ért! -----");
                        break;
                    case 'f': // Felhős idő következik
                        System.out.println("------ " + (i+1) + ". nap: FELHŐS -----");
                        creatures.stream().map(c -> {
                            c.cloudy();
                            return c;
                        }).forEachOrdered(c -> {
                            System.out.println(c.getName() + ": összesen " + c.getTravelled()
                                + " egységet tett meg eddig. (Víztartalék: "
                                + c.getWater() + ")." );
                        });
                        System.out.println("------ " + (i+1) + ". nap: Véget ért! -----");
                        break;

                    case 'e': // Esős idő következik
                        System.out.println("------ " + (i+1) + ". nap: ESŐS -----");
                        creatures.stream().map(c -> {
                            c.rainy();
                            return c;
                         }).forEachOrdered(c -> {
                            System.out.println(c.getName() + ": összesen " + c.getTravelled()
                                + " egységet tett meg eddig. (Víztartalék: "
                                + c.getWater() + ")." );
                            });
                        System.out.println("------ " + (i+1) + ". nap: Véget ért! -----");
                        break;
                    default:
                        break;
                }
                aliveCreatures=0;
                aliveCreatures = creatures.stream()
                        .filter(c -> (c.isAlive()))
                        .map(_item -> 1)
                        .reduce(aliveCreatures, Integer::sum); //Csak egy maradt-e?
                if(aliveCreatures==1) {
                    System.out.println("Verseny befejeződött a(z) " + (i+1) + ". napon, mert csak egy lény maradt életben!");
                }
                daysGoneBy = i+1;
            }
        }
        else { // Nincs részletezés
            int aliveCreatures=0;
            for(int i=0;i<days.length() && aliveCreatures!=1;i++) {
                switch (days.charAt(i)) {
                    case 'n': // Napos idő következik
                        creatures.forEach(c -> {
                            c.sunny();
                        });
                        break;
                    case 'f': // Felhős idő következik
                        creatures.forEach(c -> {
                            c.cloudy();
                        });
                        break;
                    case 'e': // Esős idő következik
                        creatures.forEach(c -> {
                            c.rainy();
                        });
                        break;
                    default:
                        break;
                }
                aliveCreatures=0;
                aliveCreatures = creatures.stream()
                        .filter(c -> (c.isAlive()))
                        .map(_item -> 1)
                        .reduce(aliveCreatures, Integer::sum); //Csak egy maradt-e?
                if(aliveCreatures==1) {
                    System.out.println("Verseny befejeződött a(z) " + (i+1) + ". napon, mert csak egy lény maradt életben!");
                }
                daysGoneBy = i+1;
            }
            
        }
        return creatures;
    }
    /**
     * simulationAnalyze eljárásnál, megkapjuk a lények gyűjteményét. 
     * Először egy maximumkeresését indítunk, mely lény tette meg a legnagyobb távot.
     * Ezek után kiszámoljuk, hány olyan lény van, aki legtöbbet tette meg és még él is. Magyarul, hogy hány győztese van a versenynek.
     * Végül, ha a több győztes van, kiíratjuk hány győztes van, hány nap telt el, a győztesek neveit, a megtett távolságukat és mennyi vizük maradt.
     * Ha egy győztes van: kiíratjuk hány nap telt el, a győztetes nevét, a megtett távolságát és mennyi vize maradt a verseny végére.
     * @param reaminedCreatures A szimulációt indító függvény visszatérési értéke. A módosított lények gyűjteménye.
     */
    public static void simulationAnalyze(ArrayList<Creatures> reaminedCreatures) {
        int maxDistance = 0;

        for (Creatures rc : reaminedCreatures) {
            if(rc.isAlive()) {
                if(rc.getTravelled() > maxDistance) {
                    maxDistance = rc.getTravelled();
                } 
            }
        }
        int gyoztesek=0;
        for(Creatures rc : reaminedCreatures) {
            if(rc.getTravelled() == maxDistance) {
                gyoztesek++;
            }
        }
        if(gyoztesek>1) {
            System.out.println("\n"+gyoztesek + "-(szeres/szörös/szoros) holtverseny alakult ki!");
            System.out.println("Összesen " + daysGoneBy + " nap telt el. A győztesek a következők:");
            gyoztesek=0;
            for(Creatures rc : reaminedCreatures) {
                if(rc.getTravelled() == maxDistance) {
                    System.out.println(++gyoztesek + ". Győztes: " + rc.getName() + " (megtett távolság: " 
                            + rc.getTravelled() + ", megmaradt víz: " 
                            + rc.getWater() + ").");
                }
            }
        }
        else {
            System.out.println("\nÖsszesen " + daysGoneBy + " nap telt el. A győztes a következő:");
            for(Creatures rc : reaminedCreatures) {
                if(rc.getTravelled() == maxDistance) {
                    System.out.println("Győztes: " + rc.getName() + " (megtett távolság: " 
                            + rc.getTravelled() + ", megmaradt víz: " 
                            + rc.getWater() + ").");
                }
            }
        }
    }
}
       