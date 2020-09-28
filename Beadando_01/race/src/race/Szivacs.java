package race;

public class Szivacs extends Creatures  {
    private final int capicity = 20; // Capicityt azonnal inicializálom és private final-ra állítom, mert többet nem kell hozzányúlni.
   
    public Szivacs(String name, int water) {
        super(name, water);
    }
    
    /**
     * sunny() eljárás lekérdezi, hogy a Szivacsunk él-e, ha igen vízkészleteiből levesz 4-et, majd lekérdezzük
     * hogy maradt e még vize, ha nem, akkor beállítjuk, hogy meghalt és a vizét 0-ra állítjuk, majd kiírjuk az eseményt.
     */
    @Override
    public void sunny() {
        if(this.isAlive()) {
            this.water-=4;
            if(this.water<0) {
                this.alive = false;
                this.water = 0;
                System.out.println(this.name + " (Szivacs): meghalt mert elfogyott a vize");
            }
        }
    }    
    /**
     * cloudy() eljárás lekérdezi, hogy a Szivacsunk él-e, ha igen vízkészleteiből levesz 1-et, majd lekérdezzük
     * hogy maradt e még vize, ha igen, előre lépünk vele 1-et.
     * Amennyiben nem, beállítjuk, hogy meghalt és a vizét 0-ra állítjuk, majd kiírjuk az eseményt.
     */
    @Override
    public void cloudy() {
        if(this.isAlive()) {
            this.water-=1;
            if(this.water>0) {
                this.travelled+=1;
            }
            else {
                this.alive = false;
                this.water = 0;
                System.out.println(this.name + " (Szivacs): meghalt mert elfogyott a vize");
            }
        }
    }
    @Override
     /**
     * rainy() eljárás lekérdezi, hogy a Szivacsunk él-e, ha igen vízkészleteihez hozzáad 6-ot, 
     * HA nem lépte túl a saját tárolóképességének a határát, ellenkező esetben beállítjuk a vizét a tárolóképességére (hogy ne lépje túl azt).
     * Ezek után lépünk 3-at előre a lénnyel.
     */
    public void rainy() {
        if(this.isAlive()) {
            if(this.capicity>=this.water+6) {
                this.water+=6;
            }
            else {
                this.water=this.capicity;
            }
            this.travelled+=3;
        }
    }
}
