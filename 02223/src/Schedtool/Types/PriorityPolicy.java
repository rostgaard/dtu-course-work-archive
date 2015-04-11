package Schedtool.Types;

/**
 *
 * @author Kim Rostgaard Christensen
 */
public enum PriorityPolicy {
    /**
     *
     */
    STATIC,
    /**
     *
     */
    RATE_MONOTONIC,
    /**
     *
     */
    DEADLINE_MONOTONIC,
    /**
     *
     */
    EARLIEST_DEADLINE_FIRST
}
