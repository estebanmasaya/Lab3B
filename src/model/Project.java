package model;

import model.matcher.ITaskMatcher;

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

    public List<Task> findTasks(ITaskMatcher matcher) {
        ArrayList tmp = new ArrayList<>();
        for (Task t : tasks) {
            if (matcher.match(t)) {
                tmp.add(t);
            }
        }
        return tmp;
    }

    public Task addTask(String description, Prio prio){
        Task newTask = new Task(description, prio, nextTaskId++);
        tasks.add(newTask);
        return newTask;
    }

    public Task removeTask(Task task){
        Task removedTask = task;
        tasks.remove(task);
        return removedTask;
    }

    public ProjectState getState(){
        if(tasks.isEmpty()) return ProjectState.EMPTY;
        for(Task t: tasks){
            if(t.getState() != TaskState.DONE){
                return ProjectState.ONGOING;
            }
        }
        return ProjectState.COMPLETED;
    }

    public LocalDate getLastUpdated(){
        if(tasks.isEmpty()) return created;
        LocalDate max = tasks.get(0).getLastUpdate();
        for(Task t : tasks){
            if((max.compareTo(t.getLastUpdate()))<0){
                max = t.getLastUpdate();
            }
        }
        return max;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getCreated() {
        return created;
    }

    public int getNextTaskId() {
        return nextTaskId;
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> newArray = new ArrayList<>();
        for(int i=0; i<tasks.size(); i++){
            newArray.add(new Task(newArray.get(i).getDescription(), newArray.get(i).getTaskPrio(), newArray.get(i).getId()));
            newArray.get(i).setState(tasks.get(i).getState());
            newArray.get(i).setTakenBy(tasks.get(i).getTakenBy());

        }
        return tasks;
    }

    @Override
    public int compareTo(Project other){
        return title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", created=" + created +
                ", nextTaskId=" + nextTaskId +
                ", tasks=" + tasks +
                '}';
    }

/*    @Override*/
/*    public String toString(){*/
/*        String info = String.format("%-10s%10s%25s%15s%15s%15s\n%-10s%10d%25s%15s%15d%15s", "Project Title", "ID", "Description", "Created", "NextTaskID", "Tasks", title, id,description,created,nextTaskId, tasks);*/
/*        return info;*/
/*    }*/

}


