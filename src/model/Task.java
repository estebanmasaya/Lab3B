package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Comparable<Task>, Serializable {

    private String description;
    private final int id;
    private String takenBy;
    private TaskState state;
    private LocalDate lastUpdate;
    private Prio taskPrio;

    protected Task(String description, Prio taskPrio, int id){
        this.description = description;
        this.id = id;
        this.taskPrio = taskPrio;
        takenBy="";
        state = TaskState.TO_DO;
        lastUpdate = LocalDate.now();
    }

    public void setTakenBy(String takenBy){
        this.takenBy = takenBy;
        lastUpdate = LocalDate.now();
    }

    public void setState(TaskState state){
        this.state = state;
        lastUpdate = LocalDate.now();
    }

    public void setTaskPrio(Prio prio){
        taskPrio = prio;
        lastUpdate = LocalDate.now();
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getTakenBy() {
        return takenBy;
    }

    public TaskState getState() {
        return state;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public Prio getTaskPrio() {
        return taskPrio;
    }

    @Override
    public int compareTo(Task obj) {
        if (taskPrio.ordinal() - obj.taskPrio.ordinal() == 0) {
            return obj.description.compareTo(description);
        }
        return taskPrio.ordinal() - obj.taskPrio.ordinal();
    }

    @Override
    public boolean equals(Object obj) {
        if(compareTo((Task) obj ) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", takenBy='" + takenBy + '\'' +
                ", state=" + state +
                ", lastUpdate=" + lastUpdate +
                ", taskPrio=" + taskPrio +
                '}';
    }
}
