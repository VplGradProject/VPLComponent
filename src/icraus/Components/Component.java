/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import com.icraus.vpl.codegenerator.Statement;
import com.sun.javafx.collections.ObservableListWrapper;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;

/**
 *
 * @author Shoka
 */
public abstract class Component {
//TODO set getters and remove setters of props to properties
    //TODO change it to use Visitor Pattern
//TODO DONE add Component Type
    //TODO add xml serializer for saving and closing
    private StringProperty uuid;
    private ObjectProperty<Statement> statement;
    //TODO CHANGE Component Ui Refrence to ID;
    private ObjectProperty<Node> uiDelegate;
    private ObservableList<Component> childern;
    private ObjectProperty<Component> parent;
    private ObjectProperty<TreeItem<Component>> root;

    public TreeItem<Component> getRoot() {
        return root.getValue();
    }

    public ObservableList<Component> getChildern() {
        return childern;
    }

    public void setChildern(ObservableList<Component> childern) {
        this.childern = childern;
    }

    public Component() {
        statement = new SimpleObjectProperty<>();
        uiDelegate = new SimpleObjectProperty<>();
        uuid = new SimpleStringProperty();
        parent = new SimpleObjectProperty<>();
        childern = new ObservableListWrapper<>(new ArrayList<>());
        root = new SimpleObjectProperty<>(new TreeItem<Component>(this));
        uuid.setValue(getType() + UUID.randomUUID().toString());
    }
    
    
    @Override
    public abstract String toString();
    
    public abstract String getType();

    public int getFlags(){ //TODO migrate to flags
        return ComponentFlags.INSERTABLE_FLAG | ComponentFlags.SELECTABLE_FLAG | ComponentFlags.REMOVABLE_FLAG;
    }
    
    public ObjectProperty<Statement> getStatement() {
        return statement;
    }

    public void setStatement(ObjectProperty<Statement> statement) {
        this.statement = statement;
    }

    public void setStatement(Statement st) {
        statement.setValue(st);
    }

    public ObjectProperty<Node> getUiDelegate() {
        return uiDelegate;
    }

    public void setUiDelegate(ObjectProperty<Node> uiDelegate) {
        this.uiDelegate = uiDelegate;
    }

    public void setUiDelegate(Node ui) {
        uiDelegate.setValue(ui);
    }

    public String getUUID() {
        return uuid.getValue();
    }

    public String addComponent(Component c) throws IllegalComponent {
        getChildern().add(c);
        c.setParent(this);
        return c.getUUID();
    }

    public void removeComponent(String uuid) throws ComponentNotFoundException {
        for (Component c : getChildern()) {
            if (c.getUUID() == uuid) {
                getChildern().remove(c);
                return;
            }
        }
        throw new ComponentNotFoundException();
    }

    public TreeItem<Component> toTreeItem() { //TODO FIXME fix root Selection
        getRoot().setValue(this);
        for (Component c : getChildern()) {
            getRoot().getChildren().add(c.toTreeItem());
        }
        return getRoot();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Component)) {
            return false;
        }

        Component tmp = (Component) obj;
        if (this.getUUID() != tmp.getUUID()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.uuid);
        return hash;
    }

    public Component getParent() {
        return parent.getValue();
    }

    public void setParent(ObjectProperty<Component> parent) {
        this.parent = parent;
    }

    public void setParent(Component _parent) {
        this.parent.setValue(_parent);
    }
    //TODO DONE add Parent Listner for each addition in children



}
