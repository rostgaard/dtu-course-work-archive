/*
 * Static configuration.
 */
package configuration;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public class Configuration {

    public static final String productName = "Network monitor";
    public static final String productVersion = "git-testing";
    public static final int RMIPort = 1099;
    public static final int Number_Of_Nodes = 11;
    public static final int Tolerable_Failures = 3;
    public static final int Initial_Admin = 0;
    public static final int multicastPort = 6789;
    public static final String initialGroup = "228.5.6.7";
    public static final int minimumDelay = 1000;
    public static final int maximumDelay = 1500;
    public static final int simulatedStandardDeviation = 5;
    public static final int simulatedMean = 25;
}
