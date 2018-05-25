/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import com.icraus.vpl.codegenerator.Statement;
import javafx.scene.Node;

/**
 *
 * @author Shoka
 */
public class SimpleComponent extends Component {

    private String type = "";
    private String componentString = "";
    private int userFlags = 0;

    public void setUserFlags(int userFlags) {
        this.userFlags = userFlags;
    }

    public SimpleComponent() {
        super();
    }

    public SimpleComponent(Statement s, Node delegate, String _type) {
        super();
        setStatement(s);
        setUiDelegate(delegate);
        type = _type;
        componentString = _type;
    }
    
    public SimpleComponent(Statement s, Node delegate, String _type, String str, int flags) {
        this(s, delegate, _type, str);
        userFlags = flags;

    }

    public SimpleComponent(Statement s, Node delegate, String _type, String str) {
        super();
        setStatement(s);
        setUiDelegate(delegate);
        type = _type;
        componentString = str;

    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getFlags() {
        return super.getFlags() | ComponentFlags.DRAGGABLE_FLAG | userFlags; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String toString() {
        return componentString;
    }

}
