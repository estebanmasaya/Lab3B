package model;

public class TestMain {
    public static void main(String[] args) {
        ProjectsManager projectsManager = new ProjectsManager();

        System.out.println(projectsManager.getProjectById(0));
    }
}
