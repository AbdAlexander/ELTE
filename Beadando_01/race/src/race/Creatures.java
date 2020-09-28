
package race;

/**
 * Creatrues egy főosztály, melyből származnak a különböző lények.
 */
public abstract class Creatures {
    protected final String name;
    protected int water;
    protected boolean alive;
    protected int travelled;

    public Creatures(String name, int water) {
        this.name = name;
        this.water = water;
        this.alive = true;
    }
    
    /**
     * sunny,cloudy és rainy metódus abstract, mivel minden egyes lény, máshogy
     * reagál rájuk, így minden egyes lénynél más és más az eljárás
     */
    public abstract void sunny();
    public abstract void cloudy();
    public abstract void rainy();
    
    
    /**
     * Csak a legfontosabb gettereket állítottam be, nem az összeset.
     */
    public boolean isAlive() {
        return alive;
    }
    public String getName() {
        return name;
    }

    public int getWater() {
        return water;
    }

    public int getTravelled() {
        return travelled;
    }
}
