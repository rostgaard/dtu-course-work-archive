package unused;

import java.util.List;

import flowgraph.datastructure.Node;
import syntaxtree.Program;
import syntaxtree.StatementList;
import syntaxtree.statement.If;
import syntaxtree.statement.Statement;
import syntaxtree.statement.While;

public class Flowgraph {

    private Node head;

    public void converter(Program p) {
        StatementList statements = p.getStmts();

        for (int i = 0; i < statements.size(); i++) {
            Statement stmt = statements.get(i);
//			Node n = null;
//            if (stmt instanceof If) {
 //               BranchNode bn = new BranchNode();
//				insert(bn);
//            } else if (stmt instanceof While) {
//                BranchNode bn = new BranchNode();
//            } else {/
//                StatementNode sn = new StatementNode();
//                insert(sn);
//            }
        }

    }

//    private void insert(StatementNode sn) {
//        if (head == null) {
//            head = sn;
//        } else {
//            if (head instanceof StatementNode) {
//                StatementNode n = (StatementNode) head;
//                n.setNext(sn);
//            }
//
////			tail = sn;
////			sn.setNext(tail);
//        }
//    }
}
