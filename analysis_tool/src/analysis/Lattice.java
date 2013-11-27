/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analysis;

/**
 *
 * @author krc
 */
public abstract interface Lattice {

    public Lattice factory();
    
    public abstract Lattice BOTTOM();
    
    public abstract boolean subsetOf(Lattice lattice);

    public Lattice union(Lattice lattice);

    public Lattice iota();

}
