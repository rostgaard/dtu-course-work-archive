package Schedtool.Model;

import Schedtool.Model.Task;
import Schedtool.Utils.LCMutils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A collection of tasks.
 * @author Kim Rostgaard Christensen
 */
public class TaskList extends ArrayList<Task> {

    Task idleTask = new Task();

    /**
     * Creates a new TaskList object
     */
    public TaskList() {
        idleTask.setName("idle");
        idleTask.setPriority(0);
    }

    /**
     * Returns the idle task
     * @return The idle task
     */
    public Task getIdleTask() {
        return idleTask;
    }

    /**
     * Sorting method. Sorts by name
     */
    public void sortByName() {
        Collections.sort(this, new TaskNameComparator());
    }

    /**
     * Sorting method, sorts by priority
     */
    public void sortByPriority() {
        Collections.sort(this, new TaskPriorityComparator());
    }

    /**
     *
     * @return
     */
    public int getLCMofPeriods() {
        int lcm = 0;
        for (Task t : this) {
            if (lcm == 0) {
                lcm = t.getPeriod();
                continue;
            } else {
                lcm = LCMutils.lcm(lcm, t.getPeriod());
            }
        }
        return lcm;
    }

    /**
     * Reads a task Set, Returns the processor utilization
     *
     * @return A double value containing the processor
     * 			utilization for the task set
     */
    public double getProcUtilization() {
        double U = 0;
        for (Task t : this) {

            int Ci = t.getWCET();  // worst case
            int Ti = t.getPeriod(); // period

            U += ((double) Ci / (double) Ti);
        }

        return U;
    }

    /**
     * Calculates the Least Upper Bound on the task set
     * @return
     */
    public double getLeastUpperBound() {
        return (double) this.size() * (Math.pow(2, 1 / (double) this.size()) - 1);
    }

    /**
     *
     * @param from
     * @param to
     * @return A svg document 
     */
    public String timelineToSVG(int from, int to) {
        String timeline = ""
                + "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>\n"
                + "<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 20010904//EN\" \n"
                + "\"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">\n"
                + "<svg xmlns=\"http://www.w3.org/2000/svg\" "
                + "xmlns:xlink=\"http://www.w3.org/1999/xlink\" xml:space=\"preserve\" >\n";
        this.add(idleTask);



        for (Task task : this) {


            String timeSlotColor = "green";
            if (task.getName().equals("idle")) {
                timeSlotColor = "blue";
            }
            if (!task.getResourceList().isEmpty()) {
                timeSlotColor = "red";
            }

            int Yoffset = 20 * (this.indexOf(task) + 1);
            int timeslotOffset = 120;
            int lineY = Yoffset + 10;
            int lineX2 = to * 10 + 10 + timeslotOffset;

            // The name of the task
            timeline += "<text style=\"fill:black; text-anchor:right;\" x=\"10\" y=\""
                    + (Yoffset + 8) + "\">" + task.getName() + "</text>";

            // Timeline
            timeline += "<line x1=\"10\" y1=\"" + lineY + "\" x2=\"" + lineX2 + "\" y2= \""
                    + lineY + "\" stroke=\"grey\"  />";
/*
            // Deadlines
            for (int i = 1; i < 5; i++) {
                timeline += "<line "
                        + "x1=\"" + i*(timeslotOffset + (task.getDeadline() * 10)) + "\" "
                        + "y1=\"" + (lineY + 3) + "\" "
                        + "x2=\"" + i*(timeslotOffset + (task.getDeadline() * 10)) + "\" "
                        + "y2=\"" + (lineY - 13) + "\" stroke=\"black\"  />";
            }

*/

            for (Integer i : task.getTimeSlotList()) {
                timeline += "<rect x=\"" + ((10 * i) + timeslotOffset) + "\" y=\"" + Yoffset
                        + "\" width=\"10\" height=\"10\" fill=\"" + timeSlotColor + "\" />\n";

            }

            // TODO plot releasetimes, timeticks(line) and deadlines;


        }
        this.remove(idleTask);

        return timeline.concat("</svg>");
    }

    /**
     *
     * @param name
     * @return
     */
    public Task find(String name) {
        Task found = null;
        for (Task t : this) {
            if (t.getName().equals(name)) {
                found = t;
                break;
            }
        }
        return found;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        String ret = "";
        for (Task t : this) {
            ret += t + "\n";
        }
        return ret;
    }

    /**
     *
     * @return
     */
    public String fullinfo() {
        String ret = "";
        for (Task t : this) {
            ret += t.fullinfo() + "\n";
        }
        return ret;
    }
}

class TaskNameComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        Task t1 = (Task) o1;
        Task t2 = (Task) o2;
        return (t1.getName().compareToIgnoreCase(t2.getName()));
    }
}

class TaskPriorityComparator implements Comparator {

    public int compare(Object o1, Object o2) {
        Task t1 = (Task) o1;
        Task t2 = (Task) o2;
        if (t1.getPriority() == t2.getPriority()) {
            return 0;
        } else if (t1.getPriority() < t2.getPriority()) {
            return 1;
        } else {
            return -1;
        }
    }
}