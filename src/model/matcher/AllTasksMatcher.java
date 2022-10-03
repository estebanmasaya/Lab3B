package model.matcher;

import model.Task;

public class AllTasksMatcher implements ITaskMatcher {

    @Override
    public boolean match(Task task) {
        return true;
    }
}
