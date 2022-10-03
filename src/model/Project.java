package model;

import model.matcher.ITaskMatcher;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project implements Comparable<Project>, Serializable {
    private final String title;
    private final int id;
    private final String description;
    private final LocalDate created;
    private int nextTaskId;
    private ArrayList<Task> tasks;

    /**
     * Creates a new project with a given title, description and id
     * Many Projects can belong to one ProjectsManager
     * @param title The title of the project
     * @param description Description of the project
     * @param id An individual id of the created project
     *           created - The current date the project was created
     *           nextTaskId - The next id a new task will get
     *           tasks - An array of Tasks (objects)
     *
     */
    protected Project(String title, String description, int id) {
        this.title = title;
        this.id = id;
        this.description = description;
        this.created = LocalDate.now();
        this.nextTaskId = 0;
        tasks = new ArrayList<>();
    }

    /**
     * Get a task by its specific task id
     * @param id Specific task id
     * @return A Task with the corresponding id
     */
    public Task getTaskById(int id) {
        for (Task t: tasks) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    /**
     * Finds all the task based on the matcher class, based on priority, taken by whom, finding all the tasks and tasks that are not done
     * @param matcher Give the corresponding matcher to the criteria you want to search for
     * @return A list of the tasks
     */
    public List<Task> findTasks(ITaskMatcher matcher) {
        ArrayList tmp = new ArrayList<>();
        for (Task t : tasks) {
            if (matcher.match(t)) {
                tmp.add(t);
            }
        }
        return tmp;
    }

    /**
     * Adds a task to a project
     * @param description Describes the task
     * @param prio The priority of the task
     * @return returns a Task object
     */
    public Task addTask(String description, Prio prio){
        Task newTask = new Task(description, prio, nextTaskId++);
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Removes a task from a project
     * @param task the task that is to be removed
     * @return returns the removed Task
     */
    public Task removeTask(Task task){
        Task removedTask = task;
        tasks.remove(task);
        return removedTask;
    }

    /**
     * Gives the state of a project
     * @return the current state of a project, either Empty, Ongoing or Done
     */
    public ProjectState getState(){
        if(tasks.isEmpty()) return ProjectState.EMPTY;
        for(Task t: tasks){
            if(t.getState() != TaskState.DONE){
                return ProjectState.ONGOING;
            }
        }
        return ProjectState.COMPLETED;
    }

    /**
     * Gets the last time a project was updated
     * @return returns the date the project was last updated
     */
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

    /**
     *
     * @return return the title of a project
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @return returns the individual integer id of a project
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return returns a string of the description of a project
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return returns the date a project was created
     */
    public LocalDate getCreated() {
        return created;
    }

    /**
     *
     * @return returns the id of the task the next task will get
     */
    public int getNextTaskId() {
        return nextTaskId;
    }

    /**
     *  gets all the tasks of a project
     * @return A list of all the tasks of a project
     */
    public ArrayList<Task> getTasks() {
        return (ArrayList<Task>) tasks.clone();
    }

    /**
     * checks if the two projects titles are the same
     * @param obj Takes a object and checks if it is a project type
     * @return returns a boolean representing if the title of the projects are the same
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Project){
            return(((Project) obj).title.equals(title));
        }
        return false;
    }

    /**
     * 
     * @param other the object to be compared.
     * @return returns 0 if the title of two project is the same, otherwise not
     */
    @Override
    public int compareTo(Project other){
        return title.compareTo(other.title);
    }

    /**
     *
     * @return returns a string representing the information of all the fields
     */
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
}


