package viceCity.models.players;

public class CivilPlayer extends BasePlayer{
    private String name;
    private final static int INITIAL_LIFE_POINTS_CIVIL_PLAYER = 50;
    private int lifePoints;

    public CivilPlayer(String name) {
        super(name, INITIAL_LIFE_POINTS_CIVIL_PLAYER);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setLifePoints(int lifePoints) {
        this.lifePoints = INITIAL_LIFE_POINTS_CIVIL_PLAYER;
    }

    @Override
    public void takeLifePoints(int points) {
        if (INITIAL_LIFE_POINTS_CIVIL_PLAYER - points >= 0){
            this.lifePoints = INITIAL_LIFE_POINTS_CIVIL_PLAYER - points;
        }
    }
}
