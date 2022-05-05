package viceCity.models.players;

public class MainPlayer extends BasePlayer{
    private final static String NAME_MAIN_PLAYER = "Tommy Vercetti";
    private final static int INITIAL_LIFE_POINTS_MAIN_PLAYER = 100;
    private int lifePoints;

    public MainPlayer() {
        super(NAME_MAIN_PLAYER, INITIAL_LIFE_POINTS_MAIN_PLAYER);
    }

    @Override
    public void setLifePoints(int lifePoints) {
        this.lifePoints = INITIAL_LIFE_POINTS_MAIN_PLAYER;
    }

    @Override
    public void takeLifePoints(int points) {
        if (INITIAL_LIFE_POINTS_MAIN_PLAYER - points >= 0){
            this.lifePoints = INITIAL_LIFE_POINTS_MAIN_PLAYER - points;
        }
    }
}
