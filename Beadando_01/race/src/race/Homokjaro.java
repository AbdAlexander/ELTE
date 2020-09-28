
package race;

public class Homokjaro extends Creatures {
    private final int capicity = 8; // Capicityt azonnal inicializálom és private final-ra állítom, mert többet nem kell hozzányúlni.
    
    public Homokjaro(String name, int water) {
        super(name, water);
    }
    
    /**
     * sunny() eljárás lekérdezi, hogy a Homorjárónk él-e, ha igen vízkészleteiből levesz 1-et, majd lekérdezzük
     * hogy maradt e még vize, ha igen, előre lépünk vele 3-at.
     * Amennyiben nem, beállítjuk, hogy meghalt és a vizét 0-ra állítjuk, majd kiírjuk az eseményt.
     */
    @Override
    public void sunny() {
        if(this.isAlive()) {
            this.water-=1;
            if(this.water>0) {
                this.travelled+=3;
            }
            else {
                this.alive = false;
                this.water = 0;
                System.out.println(this.name + " (Homokjáró): meghalt mert elfogyott a vize");
            }
        }
    }
    /**
     * cloudy() eljárásnál könnyű dolgunk van, lekérdezzük, hogy a Homorjárónk él-e,
     * ha igen, lépünk vele előre 1-et.
     */
    @Override
    public void cloudy() {
        if(this.isAlive()) {
            this.travelled+=1;
        }
    }
    /**
     * rainy() eljárásnál is könnyű dolgunk van, lekérdezzük, hogy a Homorjárónk él-e,
     * ha igen, hozzáaadunk 3-at a vizkészleteihez, HA nem lépte túl a tárolóképességét,
     * amennyiben túllépi, abban az esetben a vízkészletét beállítjuk a tárolóképességére, hogy ne lépje túl azt.
     * Ezen a napon nem lépünk vele előre.
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
        }
    }
    
}
