/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxtree;

import java.util.ArrayList;
import syntaxtree.declaration.Declaration;
import syntaxtree.statement.Statement;

/**
 *
 * @author krc
 */
public class DeclarationList extends ArrayList<Declaration>{
    @Override
    public String toString() {
        String buffer = "";
        for (Declaration d : this) {
            buffer += d + "\n";
        }
        
        return buffer;
    }
}
