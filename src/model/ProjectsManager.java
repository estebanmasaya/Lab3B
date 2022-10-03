package model;

import model.exceptions.TitleNotUniqueException;

import java.util.ArrayList;
import java.util.List;

public class ProjectsManager {

    private int nextProjectId;
    private ArrayList<Project> projects;

    /**
     * Creates a new ProjectManager by creating an arraylist of projects to it.
     * Sets the id of the next created project to 0
     */
    public ProjectsManager() {
        nextProjectId = 0;
        projects = new ArrayList<>();
    }

    /**
     * Sets the array of project to correspond to all the projects given
     * @param incomingProjects A list of projects to set as the new list of projects
     */
    public void setProjects(List<Project> incomingProjects ){
        projects.clear();
        projects = (ArrayList<Project>) incomingProjects;
        nextProjectId = getHighestId()+1;
    }

    /**
     * Checks to see if a title of a project is unique
     * @param title The title to be checked
     * @return A boolean representing if the title is unique
     */
    public boolean isTitleUnique(String title){
        for(Project p : projects){
            if(p.getTitle().equals(title)){
                return false;
            }
        }
        return true;
    }

    /**
     * Adds a project to the ProjectManager
     * @param title Gives the project a title
     * @param description Gives the project a description
     * @return returns the project added
     * @throws TitleNotUniqueException If the title of the project is taken
     */
    public Project addProject(String title, String description) throws TitleNotUniqueException{
        Project newProject = new Project(title, description, nextProjectId);
        if(isTitleUnique(title)){
            projects.add(newProject);
            nextProjectId++;
        }
        else{
            throw new TitleNotUniqueException(title + " is not unique");
        }
        return newProject;
    }

    /**
     * Removes a project from the ProjectManager
     * @param project The project to be removed
     */
    public void removeProject(Project project){
        projects.remove(project);
    }

    /**
     * Gets a project based on the projectId
     * @param id the id of a specifik project
     * @return return the project of the corresponding id, or null if it cant be found
     */
    public Project getProjectById(int id){
        for(Project p : projects){
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }

    /**
     * Gets a list of all the project in the ProjectManager
     * @return return a list of all the projects
     */
    public ArrayList<Project> getProjects() {
        return projects;
    }

    /**
     * Finds the project with the specific title
     * @param titleStr The title to find
     * @return return a List of the project
     */
    public List<Project> findProjects(String titleStr){
        List<Project> newList = new ArrayList<>();
        for(Project p : projects){
            if(p.getTitle().equals(titleStr)){
                newList.add(p);
            }
        }
        return newList;

    }

    /**
     *
     * @return returns the integer representing the id that the next created project will get
     */
    public int getNextProjectId() {
        return nextProjectId;
    }

    /**
     * Gets the id of the last project added
     * @return return an integer of the projects id
     */
    private int getHighestId(){
        if(projects.size()>0) {
            return projects.get(projects.size()-1).getId();
        }
        return 0;
    }

    /**
     *
     * @return returns a string representing the information of all the fields
     */
    @Override
    public String toString() {
        return "ProjectsManager{" +
                ", projects=" + projects +
                "nextProjectId=" + nextProjectId +
                '}';
    }
}
