
import analysis.BufferUnderflow;
import analysis.IntervalLattice;
import analysis.Analysis;
import analysis.ProgramSlicing;
import analysis.RDLattice;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;

import analysis.SignsLattice;
import flowgraph.datastructure.Node;
import output.TheLangLexer;
import output.TheLangParser;
import syntaxtree.Program;
import syntaxtree.statement.Statement;
import utilities.Sequencer;

public class Main {

    public static void printTree(CommonTree t, int indent) {
        if (t != null) {
            StringBuffer sb = new StringBuffer(indent);

            if (t.getParent() == null) {
                System.out.println(sb.toString()
                        + t.getClass().toString());
            }
            for (int i = 0; i < indent; i++) {
                sb = sb.append("   ");
            }
            for (int i = 0; i < t.getChildCount(); i++) {
                System.out.println(sb.toString() + t.getChild(i).toString());
                printTree((CommonTree) t.getChild(i), indent + 1);
            }
        }
    }

    public static void main(String args[]) throws Exception {

        boolean verbose = false;

        if (args.length < 1) {
            System.out.println("Please supply a file with source code as argument.");
            System.exit(1);
        } else if (args.length > 1) {
            verbose = true;
        }

        String inputfile = args[0];
        System.out.println("input file: " + inputfile);
        TheLangLexer lex = new TheLangLexer(new ANTLRFileStream(inputfile));
        CommonTokenStream tokens = new CommonTokenStream(lex);
        TheLangParser parser = new TheLangParser(tokens);

        try {
            TheLangParser.program_return parserResult = parser.program();
            if (parserResult != null) {
                Program program = parserResult.value;
                Sequencer seq = new Sequencer();

                // Intially set the labels.
                for (Statement s : program.getStmts()) {
                    s.setLabel(seq);
                }

                System.out.println("==== Program =====");
                System.out.println(program.getStmts().toStringWithLabel());
                System.out.println("==== End program =====");

                if (verbose) {
                    System.out.println("==== Labels =====");
                    System.out.println(program.getStmts().lables());

                    System.out.println("==== Flows =====");
                    System.out.println(program.getStmts().flow());

                    System.out.println("==== Reaching definitions =====");
                    Analysis rd = program.calculate(new RDLattice(program.getDecls()));
                    System.out.println(rd);

                    System.out.println("==== Program slice ====");
                    ProgramSlicing.execute(program.getStmts(), rd);

                    System.out.println("==== Signs analysis =====");
                    Analysis signs = program.calculate(new SignsLattice(program.getDecls()));
                    System.out.println(signs);

                    System.out.println("==== Interval analysis =====");
                    System.out.println(program.calculate(new IntervalLattice(program.getDecls())));

                }

                System.out.println("==== Buffer Underflow =====");
                for (Node node : program.underFlowCheck()) {
                    System.out.println("Potential underflow detected at label: " + node);
                }

            }
        } catch (RecognitionException e) {
            e.printStackTrace();
        }
    }
}
