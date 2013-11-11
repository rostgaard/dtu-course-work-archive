/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxtree;

import java.util.ArrayList;
import syntaxtree.statement.Statement;

/**
 *
 * @author krc
 */
public class StatementList extends ArrayList<Statement> {

    @Override
    public String toString() {
        String buffer = "";
        for (Statement s : this) {
            buffer += s +"\n";
        }
        
        return buffer;
    }
}
