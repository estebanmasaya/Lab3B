package model.matcher;

import model.Task;

public class NotDoneMatcher implements ITaskMatcher{
    public boolean match(Task task) {
        return true;
    }
}
