/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubgarden.game.go.decor;

import fr.ubx.poo.ubgarden.game.Position;
import fr.ubx.poo.ubgarden.game.go.personage.Gardener;

public class Hedgehog extends Decor {
    public Hedgehog(Position position) {
        super(position);
    }

    @Override
    public boolean walkableBy(Gardener gardener) {
        return gardener.canWalkOn(this);
    }
}
