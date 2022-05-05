package viceCity.core;

import viceCity.common.ConstantMessages;
import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private static final int TOMMY_VERCETTI_INITIAL_HEALTH = 100;
    private static final int CIVIL_PLAYER_INITIAL_HEALTH = 50;
    private Player tommyVercetti;
    private Map<String, Player> civilPlayers;
    private Deque<Gun> guns;
    private Neighbourhood gangNeighbourhood;

    public ControllerImpl() {
        tommyVercetti = new MainPlayer();
        civilPlayers = new LinkedHashMap<>();
        guns = new ArrayDeque<>();
        gangNeighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        Player civilPlayer = new CivilPlayer(name);
        civilPlayers.put(name, civilPlayer);
        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        if (type.equals("Pistol")  || type.equals("Rifle")){
            Gun gun = null;
            switch (type){
                case "Pistol":
                    gun = new Pistol(name);
                    break;
                case "Rifle":
                    gun = new Rifle(name);
                    break;
            }
            return String.format(ConstantMessages.GUN_ADDED, name, type);
        }
        return ConstantMessages.GUN_TYPE_INVALID;
    }

    @Override
    public String addGunToPlayer(String name) {
        if (guns.isEmpty()){
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }else {
            Gun gun = guns.poll();
            if (name.equals("Vercetti")){
                tommyVercetti.getGunRepository().add(gun);
                return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), tommyVercetti.getName());
            }else if (civilPlayers.containsKey(name)){
                civilPlayers.get(name).getGunRepository().add(gun);
                return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), name);
            }else {
                return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
            }
        }
    }

    @Override
    public String fight() {
        gangNeighbourhood.action(tommyVercetti, civilPlayers.values());

        if (tommyVercetti.getLifePoints() == TOMMY_VERCETTI_INITIAL_HEALTH &&
                civilPlayers.values().stream().allMatch(player -> player.getLifePoints() == CIVIL_PLAYER_INITIAL_HEALTH)) {
            return ConstantMessages.FIGHT_HOT_HAPPENED;
        }

        List<Player> deadPlayers = civilPlayers.values()
                .stream()
                .filter(player -> !player.isAlive())
                .collect(Collectors.toList());

        StringBuilder output = new StringBuilder(ConstantMessages.FIGHT_HAPPENED)
                .append(System.lineSeparator())
                .append(String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, tommyVercetti.getLifePoints()))
                .append(System.lineSeparator())
                .append(String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deadPlayers.size()))
                .append(System.lineSeparator())
                .append(String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, civilPlayers.size() - deadPlayers.size()));

        for (Player deadPlayer : deadPlayers) {
            civilPlayers.remove(deadPlayer.getName());
        }
        return output.toString();
    }
}
