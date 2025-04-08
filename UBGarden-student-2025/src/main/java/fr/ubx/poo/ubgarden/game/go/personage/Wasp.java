/*
 * Copyright (c) 2020. Laurent Réveillère
 */

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

public class Wasp extends GameObject implements Movable, PickupVisitor, WalkVisitor {

    private Direction direction;
    private boolean moveRequested = false;

    public Wasp(Game game, Position position) {
        super(game, position);
        this.direction = Direction.DOWN;
    }

    /**
     * Implémentation minimale exigée par le moteur pour le ramassage.
     * Les insectes n'interagissent pas avec les bonus EnergyBoost.
     */
    @Override
    public void pickUp(EnergyBoost energyBoost) {
        // Méthode vide pour satisfaire l'interface PickupVisitor.
    }

    /**
     * Demande un déplacement dans la direction spécifiée.
     * @param direction La direction dans laquelle la guêpe doit se déplacer.
     */
    public void requestMove(Direction direction) {
        if (direction != this.direction) {
            this.direction = direction;
            setModified(true);
        }
        moveRequested = true;
    }

    /**
     * Vérifie si le déplacement dans une direction donnée est autorisé pour la guêpe.
     * Vous devez adapter cette vérification selon les obstacles ou zones infranchissables.
     * @param direction la direction envisagée
     * @return true si le déplacement est possible, false sinon.
     */
    @Override
    public final boolean canMove(Direction direction) {
        // Exemple simplifié : les insectes peuvent toujours se déplacer.
        // Il faut ici ajouter une vérification selon le décor (arbres, obstacles, etc.).
        return true;
    }

    /**
     * Effectue le déplacement de la guêpe dans la direction donnée.
     * @param direction la direction du déplacement
     * @return La nouvelle position après déplacement.
     */
    @Override
    public Position move(Direction direction) {
        Position nextPos = direction.nextPosition(getPosition());
        Decor next = game.world().getGrid().get(nextPos);
        // Vous pouvez étendre la vérification avec 'next' pour gérer
        // les obstacles ou effets spécifiques selon le décor.
        setPosition(nextPos);
        return nextPos;
    }

    /**
     * Méthode de mise à jour appelée régulièrement par le moteur de jeu.
     * Si un déplacement a été demandé, vérifie et effectue le déplacement.
     * @param now le timestamp actuel
     */
    public void update(long now) {
        if (moveRequested) {
            if (canMove(direction)) {
                move(direction);
            }
        }
        moveRequested = false;
    }

    /**
     * Gère les dégâts subis par la guêpe.
     * Pour une guêpe, la logique peut consister à la retirer du jeu après avoir infligé sa piqûre.
     * @param damage le montant de dégâts (souvent ignoré pour une guêpe, qui meurt directement)
     */
    public void hurt(int damage) {
        // Exemple simplifié : on peut marquer la guêpe comme 'modifiée'
        // et prévoir par la suite sa suppression dans le monde.
        setModified(true);
        // Vous pouvez appeler ici une méthode de suppression, par exemple :
        // game.world().remove(this);
    }

    /**
     * Surcharge de hurt pour un dégât par défaut.
     */
    public void hurt() {
        hurt(1);
    }

    public Direction getDirection() {
        return direction;
    }
}
