
import analysis.Analysis;
import analysis.ProgramSlicing;
import analysis.UndefinedVariableException;
import analysis.lattices.IntervalLattice;
import analysis.lattices.RDLattice;
import analysis.lattices.SignsLattice;
import flowgraph.datastructure.Node;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.runtime.*;
import output.TheLangLexer;
import output.TheLangParser;
import syntaxtree.Program;
import syntaxtree.statement.Statement;
import utilities.Sequencer;

/**
 * The main bootstrapping file. Everything starts from here.
 *
 * @author krc
 */
public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * Main. Not much more to say. If you don't know why it's here, you probably
     * shouldn't be reading code.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String args[]) {

<<<<<<< HEAD
        // Verbose output?
        boolean verbose = false;
=======
        boolean verbose = true;
>>>>>>> 7556076b5e97f7969de28bb45eac39ed761d205d

        // We require at least one parameter - the input file.
        if (args.length < 1) {
            printUsage();
            System.exit(1);

        } else if (args.length > 1) { // We just assume that the second parameter is -v or similar.
            verbose = true;
        }

        String inputfile = args[0]; // Create a better name for the input file.
        TheLangLexer lex = null;

        try {
            lex = new TheLangLexer(new ANTLRFileStream(inputfile));
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Could not open file {0}", inputfile);
            System.exit(1);
        }

        // Lexing and parsing in one go.
        TheLangParser parser = new TheLangParser(new CommonTokenStream(lex));

        // Go figure out stuff about the program under the knife.
        try {
            TheLangParser.program_return parserResult = parser.program();
            Program program = parserResult.value;
            Sequencer seq = new Sequencer();

            // Intially set the labels.
            for (Statement s : program.getStmts()) {
                s.setLabel(seq);
            }

            System.out.println("==== Program =====");
            System.out.println(program.getStmts().toStringWithLabel(0));
            System.out.println("==== End program =====");

            Analysis rd = program.calculate(new RDLattice(program.getDecls()));

<<<<<<< HEAD
            // These are extra results that are nice to have, but mostly for
            // debugging purposes.
            if (verbose) {
                System.out.println("==== Labels =====");
                System.out.println(program.getStmts().lables());

                System.out.println("==== Flows =====");
                System.out.println(program.getStmts().flow());
=======
//                    System.out.println("==== Reaching definitions =====");
//                    Analysis rd = program.calculate(new RDLattice(program.getDecls()));
//                    System.out.println(rd);

//                    System.out.println("==== Program slice ====");
//                    ProgramSlicing.execute(program.getStmts(), rd);
>>>>>>> 7556076b5e97f7969de28bb45eac39ed761d205d

                System.out.println("==== Reaching definitions =====");
                System.out.println(rd);

<<<<<<< HEAD
                System.out.println("==== Signs analysis =====");
                Analysis signs = program.calculate(new SignsLattice(program.getDecls()));
                System.out.println(signs);
=======
//                    System.out.println("==== Interval analysis =====");
//                    System.out.println(program.calculate(new IntervalLattice(program.getDecls())));
>>>>>>> 7556076b5e97f7969de28bb45eac39ed761d205d

                System.out.println("==== Interval analysis =====");
                System.out.println(program.calculate(new IntervalLattice(program.getDecls())));

<<<<<<< HEAD
            }
            System.out.println("==== Program slice ====");
            ProgramSlicing.execute(program.getStmts(), rd);

            System.out.println("==== Buffer Underflow =====");
            for (Node node : program.underFlowCheck()) {
                System.out.println("Potential underflow detected at label: " + node);
            }
=======
//                System.out.println("==== Buffer Underflow =====");
//                for (Node node : program.underFlowCheck()) {
//                    System.out.println("Potential underflow detected at label: " + node);
//                }
//
//                System.out.println("==== Interval =====");
//                for (Node node : program.rangeCheck()) {
//                    System.out.println("Range check failed at: " + node);
//                }
>>>>>>> 7556076b5e97f7969de28bb45eac39ed761d205d

            System.out.println("==== Interval =====");
            for (Node node : program.rangeCheck()) {
                System.out.println("Range check failed at: " + node);
            }

        } catch (RecognitionException ex) {
            logger.log(Level.SEVERE, "Could not parse file " + inputfile, ex);
        } catch (UndefinedVariableException ex) {
            logger.log(Level.SEVERE, ex.getMessage());          
        }
    }

    /**
     * A usage message for convenience. 
     */
    private static void printUsage() {
        System.out.println("Please supply a file with source code as argument.");
    }
}
