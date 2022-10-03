package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a task in a project.
 * Many tasks can be in a project
 */
public class Task implements Comparable<Task>, Serializable {

    /**
     *
     */
    private String description;
    private final int id;
    private String takenBy;
    private TaskState state;
    private LocalDate lastUpdate;
    private Prio taskPrio;

    /**
     * Creates a new Task with a given description, priority of the task and task id.
     * Many Task objects can belong to one project
     * @param description The description of the task
     * @param taskPrio The priority (Low, Medium, High)
     * @param id The individual id of the task
     *           takenBy - A newly created task is not initialized taken by anyone
     *           state - The state gets initialized to TO DO
     *           lastUpdated - get the current date the task was created
     */
    protected Task(String description, Prio taskPrio, int id){
        this.description = description;
        this.id = id;
        this.taskPrio = taskPrio;
        takenBy="";
        state = TaskState.TO_DO;
        lastUpdate = LocalDate.now();
    }

    /**
     * Sets a person/email to whom has taken the task
     * @param takenBy Who has taken a task
     */
    public void setTakenBy(String takenBy){
        this.takenBy = takenBy;
        lastUpdate = LocalDate.now();
    }

    /**
     * Gives a task a current state
     * @param state Either "To do", "In Progress" or "Done"
     */
    public void setState(TaskState state){
        this.state = state;
        lastUpdate = LocalDate.now();
    }

    /**
     * Sets a task a priority
     * @param prio Either "Low", "Medium" or "High"
     */
    public void setTaskPrio(Prio prio){
        taskPrio = prio;
        lastUpdate = LocalDate.now();
    }

    /**
     * Gets the description of a task
     * @return A string representing the tasks description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the id of a task
     * @return An in representing the id of a specific task
     */
    public int getId() {
        return id;
    }

    /**
     * get the person who has taken the task
     * @return A string of the person/email
     */
    public String getTakenBy() {
        return takenBy;
    }

    /**
     * get the state of a task
     * @return the state, eaither "To do", "In Progress" or "Done"
     */
    public TaskState getState() {
        return state;
    }

    /**
     * get the date last time the task was updated/created
     * @return the date
     */
    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    /**
     * get the priority of a task
     * @return eaither, "Low", "Medium" or "High"
     */
    public Prio getTaskPrio() {
        return taskPrio;
    }

    /**
     * Compares two tasks
     * if two tasks has the same priority, compare the descriptions to see if same
     * else compare if the priorty is the same.
     * @param obj the object to be compared
     * @return 0 if two tasks have the same priority or description
     */
    @Override
    public int compareTo(Task obj) {
        if (taskPrio.ordinal() - obj.taskPrio.ordinal() == 0) {
            return obj.description.compareTo(description);
        }
        return taskPrio.ordinal() - obj.taskPrio.ordinal();
    }

    /**
     * checks if two tasks are the same
     * @param obj that gets downcast to a task
     * @return returns a boolean representing if the tasks are the same
     */
    @Override
    public boolean equals(Object obj) {
        if(compareTo((Task) obj ) == 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Task:" +
                "description: " + description + '\'' +
                ", id: " + id +
                ", takenBy: " + takenBy + '\'' +
                ", state: " + state +
                ", lastUpdate: " + lastUpdate +
                ", taskPrio: " + taskPrio;
    }
}
