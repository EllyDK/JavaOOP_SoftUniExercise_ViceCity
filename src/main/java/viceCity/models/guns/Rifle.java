package viceCity.models.guns;

public class Rifle extends BaseGun{
    private final static int INITIAL_BULLETS_PER_BARREL_RIFLE = 50;
    private final static int INITIAL_TOTAL_BULLETS = 500;
    private final static int BULLETS_SHOOT_COUNT_RIFLE = 5;
    private String name;
    private int bulletsPerBarrel;
    private int totalBullets;

    public Rifle(String name) {
        super(name, INITIAL_BULLETS_PER_BARREL_RIFLE, INITIAL_TOTAL_BULLETS);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setBulletsPerBarrel(int bulletsPerBarrel) {
        this.bulletsPerBarrel = INITIAL_BULLETS_PER_BARREL_RIFLE;
    }

    @Override
    public void setTotalBullets(int totalBullets) {
        this.totalBullets = INITIAL_TOTAL_BULLETS;
    }

    @Override
    public int fire() {
        if (bulletsPerBarrel <= 0){
            if (totalBullets > 0){
                bulletsPerBarrel = INITIAL_BULLETS_PER_BARREL_RIFLE;
                totalBullets = totalBullets - INITIAL_BULLETS_PER_BARREL_RIFLE;
            }
        }
        return bulletsPerBarrel - BULLETS_SHOOT_COUNT_RIFLE;
    }
}
