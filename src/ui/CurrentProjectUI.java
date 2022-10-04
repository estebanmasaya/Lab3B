package ui;

import model.matcher.*;
import model.*;
import model.matcher.TakenByMatcher;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;

/**
 * User interactions for a specific project, current project.
 * The user selects actions on current project in the projectLoop method.
 */
class CurrentProjectUI {
    private Project currentProject;
    private final Scanner scan;

    // package private visibility - only visible to other classes in
    // package ui - intended for MainUI.
    CurrentProjectUI(Scanner scan) {
        this.scan = scan;
        this.currentProject = null; // TODO: Ugly!
    }

    void setCurrentProject(Project project) {
        this.currentProject = project;
        projectLoop();
    }

    Project getCurrentProject() {
        return currentProject;
    }

    void projectLoop() {
        char choice;
        do {
            printCurrentProjectMenu();
            choice = InputUtils.scanAndReturnFirstChar(scan);

            switch (choice) {
                case 'T':
                    viewTasks(new AllTasksMatcher());
                    break;
                case 'P':
                    System.out.println("Give the name of the person/email");
                    String name = scan.nextLine();
                    viewTasks(new TakenByMatcher(name));
                    break;
                case 'N':
                    viewTasks(new NotDoneMatcher());
                    break;
                case 'H':
                    viewTasks(new PrioMatcher(Prio.High));
                    break;
                case 'A':
                    addTask();
                    break;
                case 'U':
                    updateTask();
                    break;
                case 'R':
                    deleteTask();
                    break;
                case 'X':
                    break;
                default:
                    System.out.println("Unknown command");
            }

        } while (choice != 'X');
    }

    private void viewTasks(ITaskMatcher matcher) {
        System.out.println(currentProject.toString());
        List<Task> tasks = currentProject.findTasks(matcher);
        printTasks(tasks);
    }

    private void addTask() {
        System.out.print("Description? ");
        String descr = scan.nextLine();
        System.out.print("Priority (L)ow, (M)edium, (H)igh? ");
        char prioChar = InputUtils.scanAndReturnFirstChar(scan);
        Prio prio = prioChar == 'H' ? Prio.High : prioChar == 'L' ? Prio.Low : Prio.Medium;
        currentProject.addTask(descr, prio);
    }

    private void updateTask() {
        System.out.print("Task id? ");
        int id = scan.nextInt();
        scan.nextLine(); //remove "new line" from scanner buffer
        Task task = currentProject.getTaskById(id);
        if (task != null) {
            System.out.println(task);
            System.out.print("New state (T)odo (D)one? ");
            char stateChar = InputUtils.scanAndReturnFirstChar(scan);
            if (stateChar == 'T') {
                System.out.print("Taken by (name or email address)? ");
                String emailStr = scan.nextLine();
                task.setState(TaskState.TO_DO);
                task.setTakenBy(emailStr);
            }
            else if(stateChar == ('D')) {
                task.setState(TaskState.DONE);
            }
        } else {
            System.out.println("Id not found.");
        }
    }

    private void deleteTask(){
        System.out.print("Task id?");
        int id = scan.nextInt();
        scan.nextLine();
        Task task = currentProject.getTaskById(id);
        if (task != null){
            currentProject.removeTask(task);
        }
        else{
            System.out.println("Id not found.");
        }
    }

    private void printCurrentProjectMenu() {
        System.out.println("--- Manage " + currentProject.getTitle() + " ---");
        System.out.println("T - list all tasks");
        System.out.println("N - list tasks not done");
        System.out.println("P - list tasks of a specific person");
        System.out.println("H - list high priority tasks");
        System.out.println("A - add task");
        System.out.println("U - update task");
        System.out.println("R - remove task");
        System.out.println("X - exit project menu");
        System.out.println("----------");
    }

    private void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added");
        } else {
            for (Task task : tasks) {
                System.out.println(task.toString());
            }
        }
    }
}
