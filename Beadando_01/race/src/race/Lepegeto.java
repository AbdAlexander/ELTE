package race;

public class Lepegeto extends Creatures {
    private final int capicity = 12; // Capicityt azonnal inicializálom és private final-ra állítom, mert többet nem kell hozzányúlni.
    
    public Lepegeto(String name, int water) {
        super(name, water);
    }
    /**
     * sunny() eljárás lekérdezi, hogy a Lépgetőnk él-e, ha igen vízkészleteiből levesz 2-öt, majd lekérdezzük
     * hogy maradt e még vize, ha igen, előre lépünk vele 1-et.
     * Amennyiben nem, beállítjuk, hogy meghalt és a vizét 0-ra állítjuk, majd kiírjuk az eseményt.
     */
    @Override
    public void sunny() {
        if(this.isAlive()) {
            this.water-=2;
            if(this.water>0) {
                this.travelled+=1;
            }
            else {
                this.alive = false;
                this.water = 0;
                System.out.println(this.name + " (Lépegető): meghalt mert elfogyott a vize");
            }
        }
    }
    @Override
     /**
     * cloudy() eljárás lekérdezi, hogy a Lépegetőnk él-e, ha igen vízkészleteiből levesz 1-et, majd lekérdezzük
     * hogy maradt e még vize, ha igen, előre lépünk vele 2-öt.
     * Amennyiben nem, beállítjuk, hogy meghalt és a vizét 0-ra állítjuk, majd kiírjuk az eseményt.
     */
    public void cloudy() {
        if(this.isAlive()) {
            this.water-=1;
            if(this.water>0) {
                this.travelled+=2;
            }
            else {
                this.alive = false;
                this.water = 0;
                System.out.println(this.name + " (Lépegető): meghalt mert elfogyott a vize");
            }
        }
    }
    /**
     * rainy() eljárás lekérdezi, hogy a Lépegetőnk él-e, ha igen vízkészleteihez hozzáad 3-at, 
     * HA nem lépte túl a saját tárolóképességének a határát, ellenkező esetben beállítjuk a vizét a tárolóképességére (hogy ne lépje túl azt).
     * Ezek után lépünk 1-et előre a lénnyel.
     */
    @Override
    public void rainy() {
        if(this.isAlive()) {
            if(this.capicity>=this.water+3) {
                this.water+=3;
            }
            else {
                this.water=this.capicity;
            }
            this.travelled+=1;
        }
    }
}
