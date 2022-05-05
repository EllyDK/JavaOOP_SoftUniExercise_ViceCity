package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;

public class GangNeighbourhood implements Neighbourhood {
    @Override
    public void action(Player tommyVercetti, Collection<Player> civilPlayers) {
        Repository<Gun> tommyGunRepository = tommyVercetti.getGunRepository();
        Deque<Gun> tommyGunsDeque = new ArrayDeque<>(tommyGunRepository.getModels());
        Deque<Player> civilPlayersDeque = new ArrayDeque<>(civilPlayers);

        Player currentCivilPlayer = civilPlayersDeque.poll();
        Gun currentTommyGun = tommyGunsDeque.poll();
        while (currentCivilPlayer != null && currentTommyGun != null) {

            while (currentTommyGun.canFire() && currentCivilPlayer.isAlive()) {
                currentCivilPlayer.takeLifePoints(currentTommyGun.fire());
            }

            if (currentTommyGun.canFire()) {
                currentCivilPlayer = civilPlayersDeque.poll();
            } else {
                currentTommyGun = tommyGunsDeque.poll();
            }
        }

        for (Player civilPlayer : civilPlayers) {

            if (civilPlayer.isAlive()) {
                Repository<Gun> civilPlayerGunRepository = civilPlayer.getGunRepository();
                Deque<Gun> civilPlayerGunsDeque = new ArrayDeque<>(civilPlayerGunRepository.getModels());
                Gun currentCivilPlayerGun = civilPlayerGunsDeque.poll();

                while (currentCivilPlayerGun != null) {
                    while (currentCivilPlayerGun.canFire() && tommyVercetti.isAlive()) {
                        tommyVercetti.takeLifePoints(currentCivilPlayerGun.fire());
                    }

                    if (!tommyVercetti.isAlive()) {
                        return;
                    }

                    currentCivilPlayerGun = civilPlayerGunsDeque.poll();

                }
            }
        }
    }
}
