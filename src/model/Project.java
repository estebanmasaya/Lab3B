package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project implements Comparable<Project>, Serializable {
    private String title;
    private int id;
    private String description;
    private LocalDate created;
    private int nextTaskId;
    private ArrayList<Task> tasks;

    protected Project(String title, String description, int id) {
        this.title = title;
        this.id = id;
        this.description = description;
        this.created = LocalDate.now();
        this.nextTaskId = 0;
        tasks = new ArrayList<>();
    }

    public Task getTaskById(int id) {
        for (Task t: tasks) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public List<Task> findTask(ItaskMatcher matcher) {

    }
}
