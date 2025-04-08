package fr.ubx.poo.ubgarden.game;

import fr.ubx.poo.ubgarden.game.go.personage.Gardener;
import fr.ubx.poo.ubgarden.game.go.personage.Hornet;
import fr.ubx.poo.ubgarden.game.go.personage.Wasp;


public class Game {

    private final Configuration configuration;
    private final World world;
    private final Gardener gardener;
    private final Hornet hornet;
    private final Wasp wasp;

    private boolean switchLevelRequested = false;
    private int switchLevel;
    public Game(World world, Configuration configuration, Position gardenerPosition,Position waspPosition,Position hornetPosition) {
        this.configuration = configuration;
        this.world = world;
        gardener = new Gardener(this, gardenerPosition);
        wasp = new Wasp(this, waspPosition);
        hornet = new Hornet(this, hornetPosition);
    }

    public Configuration configuration() {
        return configuration;
    }

    public Gardener getGardener() {
        return this.gardener;
    }
    public Wasp getWasp() {
        return this.wasp;
    }
    public Hornet getHornet() {
        return this.hornet;
    }

    public World world() {
        return world;
    }

    public boolean isSwitchLevelRequested() {
        return switchLevelRequested;
    }

    public int getSwitchLevel() {
        return switchLevel;
    }

    public void requestSwitchLevel(int level) {
        this.switchLevel = level;
        switchLevelRequested = true;
    }

    public void clearSwitchLevel() {
        switchLevelRequested = false;
    }

}
