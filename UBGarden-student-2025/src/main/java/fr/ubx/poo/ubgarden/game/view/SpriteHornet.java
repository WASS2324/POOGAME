/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.ubgarden.game.view;

import fr.ubx.poo.ubgarden.game.Direction;
import fr.ubx.poo.ubgarden.game.go.personage.Gardener;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import fr.ubx.poo.ubgarden.game.Direction;
import fr.ubx.poo.ubgarden.game.go.personage.Hornet;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import fr.ubx.poo.ubgarden.game.Direction;
import fr.ubx.poo.ubgarden.game.go.personage.Hornet;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;import fr.ubx.poo.ubgarden.game.Direction;
import fr.ubx.poo.ubgarden.game.go.personage.Hornet;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


import fr.ubx.poo.ubgarden.game.Direction;
import fr.ubx.poo.ubgarden.game.go.personage.Hornet;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class SpriteHornet extends Sprite {

    public SpriteHornet(Pane layer, Hornet hornet) {
        super(layer, null, hornet);
        updateImage();
    }

    @Override
    public void updateImage() {
        Hornet hornet = (Hornet) getGameObject();
        Image image = getImage(hornet.getDirection());
        setImage(image);
    }

    public Image getImage(Direction direction) {
        // Assurez-vous que ImageResourceFactory possède une méthode getHornet qui prend en compte la direction
        return ImageResourceFactory.getInstance().getHornet(direction);
    }
}