package viceCity.models.guns;

public class Pistol extends BaseGun{
    private final static int INITIAL_BULLETS_PER_BARREL_PISTOL = 10;
    private final static int INITIAL_TOTAL_BULLETS_PISTOL = 100;
    private final static int BULLETS_SHOOT_COUNT_PISTOL = 1;
    private int bulletsPerBarrel;
    private int name;
    private int totalBullets;

    public Pistol(String name) {
        super(name, INITIAL_BULLETS_PER_BARREL_PISTOL, INITIAL_TOTAL_BULLETS_PISTOL);
    }

    @Override
    public void setBulletsPerBarrel(int bulletsPerBarrel) {
        this.bulletsPerBarrel = INITIAL_BULLETS_PER_BARREL_PISTOL;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public void setTotalBullets(int totalBullets) {
        this.totalBullets = INITIAL_TOTAL_BULLETS_PISTOL;
    }

    @Override
    public int fire() {
        if (bulletsPerBarrel <= 0){
            if (totalBullets > 0){
                bulletsPerBarrel = INITIAL_BULLETS_PER_BARREL_PISTOL;
                totalBullets = totalBullets - INITIAL_BULLETS_PER_BARREL_PISTOL;
            }
        }
        return bulletsPerBarrel - BULLETS_SHOOT_COUNT_PISTOL;
    }
}
