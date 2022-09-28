package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Comparable<T>, Serializable {

    private String description;
    private int id;
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

    }

    public void setState(TaskState state){

    }

    public void setTaskPrio(Prio prio){

    }

    @Override
    public void CompareTo(){

    }


    @Override
    public int compareTo(T o) {
        return 0;
    }
}
