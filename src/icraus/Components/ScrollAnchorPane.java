/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import icraus.Components.event.CanvasDragEventHandler;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Shoka
 */
public class ScrollAnchorPane extends ScrollPane implements Selectable {

    private Component parentComponent;

    public ScrollAnchorPane(Component _parent) {
        setPrefWidth(USE_PREF_SIZE);
        setPrefHeight(USE_PREF_SIZE);
        parentComponent = _parent;
        setContent(new ContentPane());
        parentComponent.getChildern().addListener((Observable e) -> {
            drawChildren();
        });
        setId("GUI" + parentComponent.getUUID());

        getContent().addEventHandler(DragEvent.ANY, new CanvasDragEventHandler());
    }

    @Override
    public String getParentComponentUuid() {
        return parentComponent.getUUID();
    }

    protected void drawChildren() {

        ObservableList<Component> lst = parentComponent.getChildern();
        ((AnchorPane) getContent()).getChildren().clear();
        double y = 20;
        double x = 150;
        for (Component c : lst) {
            Node value = c.getUiDelegate();
            value.setLayoutY(y);
            value.setLayoutX(x);
            ((AnchorPane) getContent()).getChildren().add(value);
            y += 100;
        }

    }
}

class ContentPane extends AnchorPane {

    public ContentPane() {
        setWidth(2000);
        setHeight(2000);
        setPrefHeight(USE_PREF_SIZE);
        setPrefWidth(USE_PREF_SIZE);
    }

}
