package Simulator.Model;

/**
 * A mapping class for modeling the relationship between resources and tasks
 * @author Kim Rostgaard Christensen
 */
public class Usage {
    Task task;
    Resource resource;
    int criticalDuration;

    /**
     * Creates a new Usage object for keeping track of the resource usage of
     * tasks.
     * @param t The task using the resource
     * @param r The resource used by the task
     * @param criticalDuration
     */
    public Usage(Task t, Resource r, int criticalDuration) {
        this.task = t;
        this.resource = r;
        this.criticalDuration = criticalDuration;
    }
}
