/**
 * 
 */
package mjava;

import org.antlr.runtime.ANTLRFileStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;

import java.io.IOException;
import java.util.*;

/**
 * @author Kasper
 *
 */
public class MJavaCC {

	/**
	 * @param args
	 * @throws RecognitionException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws RecognitionException, IOException {
		MJavaLexer lex = new MJavaLexer(new ANTLRFileStream("src.mjava"));
        CommonTokenStream tokens = new CommonTokenStream(lex);

        MJavaParser parser = new MJavaParser(tokens);
        List<String> strings = parser.prog(); // launch parsing
        for (String s:strings){
        	System.out.print(s + " ");
        }
	}

}
