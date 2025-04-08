/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubgarden.game.view;

import fr.ubx.poo.ubgarden.game.Direction;
import fr.ubx.poo.ubgarden.game.go.personage.Wasp;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import fr.ubx.poo.ubgarden.game.Direction;
import fr.ubx.poo.ubgarden.game.go.personage.Gardener;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SpriteWasp extends Sprite {

    public SpriteWasp(Pane layer, Wasp wasp) {
        super(layer, null, wasp);
        updateImage();
    }

    @Override
    public void updateImage() {
        Wasp wasp = (Wasp) getGameObject();
        Image image = getImage(wasp.getDirection());
        setImage(image);
    }

    public Image getImage(Direction direction) {
        // Assurez-vous que ImageResourceFactory possède une méthode getWasp qui prend en compte la direction
        return ImageResourceFactory.getInstance().getWasp(direction);
    }
}