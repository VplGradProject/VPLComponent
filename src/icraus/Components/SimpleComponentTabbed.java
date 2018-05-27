/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package icraus.Components;

import com.icraus.vpl.codegenerator.CodeBlock;
import com.icraus.vpl.codegenerator.CodeBlockBody;
import com.icraus.vpl.codegenerator.CodeBlockHead;
import com.icraus.vpl.codegenerator.Statement;
import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.control.Tab;

public class SimpleComponentTabbed extends SimpleComponent implements Pageable {

    private Tab tab;

    public SimpleComponentTabbed() {
    }

    public SimpleComponentTabbed(Statement s, Node delegate, String _type) {
        super(s, delegate, _type);
        tab = new Tab(_type, new ScrollAnchorPane(this));
    }

    public SimpleComponentTabbed(CodeBlockHead head, Node delegate, String _type) {
        super(null, delegate, _type);
        tab = new Tab("", new ScrollAnchorPane(this));
        CodeBlock blk = new CodeBlock();
        blk.setHead(head);
        blk.setBody(new CodeBlockBody());
        setStatement(blk);
        getChildern().addListener((Observable e) -> {
            CodeBlockBody body = blk.getBody();
            body.getChildren().clear();
            for (Component c : getChildern()) {
                body.getChildren().add(c.getStatement());
            }
        });
        
    }

    

    @Override
    public int getFlags() {
        return super.getFlags() | ComponentFlags.PAGEABLE_FLAG; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Tab getTab() {
        return tab;
    }

}
