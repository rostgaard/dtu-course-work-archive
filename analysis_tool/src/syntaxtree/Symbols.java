/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package syntaxtree;

import static syntaxtree.BooleanOperation.*;
import static syntaxtree.ArithmeticOperation.*;

/**
 *
 * @author krc
 */
public final class Symbols {
    
    public static final String SEPERATOR = " ";
    public static final String INDENTION = SEPERATOR + SEPERATOR;
    public static final String LPARAN    = "(";
    public static final String RPARAN    = ")";
    public static final String WHILE     = "while";
    public static final String DO        = "do";
    public static final String OD        = "od";
    public static final String IF        = "if";
    public static final String ELSE      = "else";
    public static final String WRITE     = "write";
    public static final String READ      = "read";
    public static final String SKIP      = "skip";
    
    public static String symbolOf(BooleanOperation bo ) {
        switch (bo) {
            case AND:
                return "&";
            case OR:
                return "|";
        }
        return null;
    }

    public static String symbolOf(ArithmeticOperation ao ) {
        switch (ao) {
            case DIVISION:
                return "/";
            case MINUS:
                return "-";
            case MULTIPLICATION:
                return "*";
            case PLUS:
                return "+";
        }
        return null;
    }
}
