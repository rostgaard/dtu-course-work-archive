package Schedtool.Types;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public enum LockingPolicy {
    /**
     * No policy
     */
    NONE,
    /**
     * Priority inheritance policy - potentially unsafe
     */
    PRIORITY_INHERITANCE,
    /**
     * Priority ceiling protocol - safe from deadlocking
     */
    PRIORITY_CEILING,
    /**
     * Stack resource protocol - multi unit
     */
    STACK_RESOURCE
}
