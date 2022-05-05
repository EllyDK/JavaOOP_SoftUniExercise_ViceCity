package viceCity.models.players;

import viceCity.common.ExceptionMessages;
import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

public abstract class BasePlayer implements Player{
    private String name;
    private int lifePoints;
    private Repository<Gun> gunRepository;

    public BasePlayer(String name, int lifePoints) {
        setName(name);
        setLifePoints(lifePoints);
        setGunRepository(gunRepository);

    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException
                    (ExceptionMessages.PLAYER_NULL_USERNAME);
        }
        this.name = name;
    }

    public void setLifePoints(int lifePoints) {
        if (lifePoints < 0){
            throw new IllegalArgumentException
                    (ExceptionMessages.PLAYER_LIFE_POINTS_LESS_THAN_ZERO);
        }
        this.lifePoints = lifePoints;
    }

    public void setGunRepository(Repository<Gun> gunRepository) {
        //this.gunRepository = gunRepository;
        //this.gunRepository = new GunRepository;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLifePoints() {
        return this.lifePoints;
    }

    @Override
    public boolean isAlive() {
        return this.lifePoints > 0;
    }

    @Override
    public Repository<Gun> getGunRepository() {
        return this.gunRepository;
    }

    @Override
    public abstract void takeLifePoints(int points);
}
