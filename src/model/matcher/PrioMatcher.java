package model.matcher;

import model.Prio;
import model.Task;

public class PrioMatcher implements ITaskMatcher{
    private Prio prio;

    public PrioMatcher(Prio prio) {
        this.prio = prio;
    }

    public boolean match(Task task) {
        return prio.equals(task.getTaskPrio());
    }
}
