package model;

import model.exceptions.TitleNotUniqueException;

import java.util.ArrayList;
import java.util.List;

public class ProjectsManager {

    private int nextProjectId;
    private ArrayList<Project> projects;

    /**
     *
     */
    public ProjectsManager() {
        this.nextProjectId = 0;
        projects = new ArrayList<>();
    }

    public void setProjects(List<Project> incomingProjects ){
        projects.clear();
        projects=(ArrayList<Project>) incomingProjects;
        nextProjectId=getHighestId()+1;
    }

    public boolean isTitleUnique(String title){
        for(Project p : projects){
            if(p.getTitle().equals(title)){
                return false;
            }
        }
        return true;
    }

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

    public void removeProject(Project project){
        projects.remove(project);
    }

    public Project getProjectById(int id){
        for(Project p : projects){
            if(p.getId()==id){
                return p;
            }
        }
        return null;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public List<Project> findProjects(String titleStr){
        List<Project> newList = new ArrayList<>();
        for(Project p : projects){
            if(p.getTitle().equals(titleStr)){
                newList.add(p);
            }
        }
        return newList;
        // findProjects i ProjectsManager returnerar en lista med referenser. Vill vi inte göra en deep copy här också?
    }

    private int getHighestId(){
        return projects.get(projects.size()-1).getId();
    }

}
