package model;

import java.util.ArrayList;
import java.util.List;

public class ProjectsManager {

    private int nextProjectId;
    private ArrayList<Project> projects;

    public ProjectsManager() {
        this.nextProjectId = 0;
        projects = new ArrayList<>();
    }

    public void setProjects(List<Project> incomingProjects ){
        projects.clear();
        nextProjectId = incomingProjects.get(incomingProjects.size()-1).getId()+1;
        for(Project p : incomingProjects){
            projects.add(p);
        }
    }

    public boolean isTitleUnique(String title){
        for(Project p : projects){
            if(p.getTitle().equals(title)){
                return false;
            }
        }
        return true;
    }

    public void addProject(String title, String description){
        for(Project p : projects){
            if(p.getTitle().equals(title));
            // KASTA EXCEPTION!!!!
            else{
                projects.add(new Project(title, description, nextProjectId++));
            }
        }
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
