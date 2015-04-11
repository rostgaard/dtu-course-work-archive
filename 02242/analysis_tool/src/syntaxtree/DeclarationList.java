package syntaxtree;

import analysis.UndefinedVariableException;
import java.util.HashSet;
import syntaxtree.declaration.Declaration;
import syntaxtree.expression.Variable;

/**
 *
 * @author krc
 */
public class DeclarationList extends HashSet<Declaration> {

    @Override
    public String toString() {
        String buffer = "";
        for (Declaration d : this) {
            buffer += d + "\n";
        }

        return buffer;
    }

    public Declaration lookup(Variable var) {
        for (Declaration decl : this) {
            if (decl.getId().equals(var)) {
                return decl;
            }
        }

        throw new UndefinedVariableException("No previous declaration for \"" + var + "\"");
    }
}
