package compiler;

import compiler.Exceptions.*;
import compiler.Phases.Backend;
import compiler.Phases.Analysis;
import compiler.Phases.Frontend;
import compiler.IR.*;

public class Compiler {

	private String filename;
	private String outputfilename;
		
	private boolean debug;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Compiler c = new Compiler(args);
			c.compile();
		} 
		catch (CompilerError e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}
	}

	public Compiler(String[] args) throws CompilerError {
		//
		// here we should inspect the arguments
		//

		if (args.length==0) {
			help();
		}

		this.filename = null;
		this.debug = false;

		for (int i=0;i<args.length;i++) {
			String arg = args[i];
			if (arg.charAt(0)!='-') {

				if (i!=args.length-1) {
					throw new CompilerError("Filename must be last argument");
				}
				
				//
				// store the filename
				//

				this.filename = args[i];
			} else {
				if (arg.equals("-v")) {
					this.debug=true;
					continue;
				}
				if (arg.equals("-o")) {

					if (i==args.length) {
						throw new CompilerError("argument for -o is missing");
					}
					
					this.outputfilename = args[i++];
					continue;
				}
			}
		}
		
		if (this.filename == null) {
			throw new CompilerError("No filename specified");
		}

	}

	public void compile() throws CompilerError
	{
		IR ir;
		
		System.out.print("Parsing... ");

		try {
			ir = Frontend.parse(this.filename);
		} catch (ParseError e) {
			throw new CompilerError("Parse Error: "+e.getMessage());
		}
		
		System.out.println("done.");
		
		if (this.isDebug()) {
			ir.prettyPrint();
		}
		
		System.out.print("Analysing... ");

		try {
			Analysis.analyse(ir);
		} catch (TypeCheckerException e) {
			throw new CompilerError("TypeCheckError "+e.getMessage());
		}

		System.out.println("done.");
		
		System.out.print("Generating code... ");

		Backend.analyse(ir);
		
		System.out.println("done.");
	}
	
	private void help() {
		
		System.err.println("\n");
		System.err.println("MiniJava compiler\n");
		System.err.println("====================\n\n");
		System.err.println("Invoke with \"java -jar path/to/jar/file \" or via GUI.");
		System.err.println("arguments: [-v] filename\n\n");
		System.err.println("  -v            be verbose\n");
		System.err.println("  filename      source filename\n");
		System.exit(-1);
	}

	public String getFilename() {
		return filename;
	}

	public String getOutputfilename() {
		return outputfilename;
	}

	public boolean isDebug() {
		return debug;
	}
	
}
