package fr.ubx.poo.ubgarden.game.go.personage;

import fr.ubx.poo.ubgarden.game.Direction;
import fr.ubx.poo.ubgarden.game.Game;
import fr.ubx.poo.ubgarden.game.Position;
import fr.ubx.poo.ubgarden.game.go.GameObject;
import fr.ubx.poo.ubgarden.game.go.Movable;
import fr.ubx.poo.ubgarden.game.go.PickupVisitor;
import fr.ubx.poo.ubgarden.game.go.WalkVisitor;
import fr.ubx.poo.ubgarden.game.go.bonus.EnergyBoost;
import fr.ubx.poo.ubgarden.game.go.decor.Decor;

public class Gardener extends GameObject implements Movable, PickupVisitor, WalkVisitor {

    private  int energy;
    private Direction direction;
    private boolean moveRequested = false;

    public Gardener(Game game, Position position) {
        super(game, position);
        this.direction = Direction.DOWN;
        this.energy = game.configuration().gardenerEnergy();
    }

    @Override
    public void pickUp(EnergyBoost energyBoost) {
        System.out.println("I am taking the boost, I should do something ...");
    }

    public int getEnergy() {
        return this.energy;
    }

    public void requestMove(Direction direction) {
        if (direction != this.direction) {
            this.direction = direction;
            setModified(true);
        }
        moveRequested = true;
    }

    @Override
    public final boolean canMove(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        if (!game.world().getGrid().inside(nextPos)) {
            System.out.println("Déplacement refusé : hors limites.");
            return false;
        }
        Decor decor = game.world().getGrid().get(nextPos);
        if (decor != null && !decor.walkableBy(this)) {
            return false; // Ne peut pas bouger si le décor n'est pas "marchable"
        }
        return true;
    }

    @Override
    public Position move(Direction direction) {
        if (!canMove(direction)) {
            System.out.println("Déplacement bloqué");
            return getPosition();  // Ne bouge pas
        }
        Position nextPos = direction.nextPosition(getPosition());
        setPosition(nextPos);  // Déplace le jardinier
        System.out.println("Nouveau lieu : " + nextPos);
        return nextPos;
    }

    public void update(long now) {
        if (moveRequested) {
            System.out.println("Update : décalage demandé en direction : " + direction);
            move(direction);
        }
        moveRequested = false;
    }

    public void hurt(int damage) {
        // Supposons que energy soit modifiable (actuellement final)
        // Vous devrez retirer le "final" de la déclaration de energy
        this.energy -= damage;
    }

    public void hurt() {
        hurt(1);
    }

    public Direction getDirection() {
        return direction;
    }
}