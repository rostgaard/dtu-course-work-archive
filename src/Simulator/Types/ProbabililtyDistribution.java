package Simulator.Types;

/**
 * Enumeration to type the different distributions
 * @author Kim Rostgaard Christensen
 */
public enum ProbabililtyDistribution {

    /**
     * Normal uniform distribution, every outcome is equaly likely
     */
    UNIFORM,
    /**
     * Standard gaussian distribution - not yet implemented
     */
    GAUSSIAN,
    /**
     * Use no randomization, always assume worst case
     */
    NONE
}
