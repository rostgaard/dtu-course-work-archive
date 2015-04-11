/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis;

/**
 *
 * @author krc
 */
public enum Sign {

    P,
    Z,
    N;

    public int toIndex() {
        switch (this) {
            case P:
                return 2;
            case Z:
                return 1;
            case N:
                return 0;
        }

        return 0;
    }
}