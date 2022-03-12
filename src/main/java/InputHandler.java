package main.java;

import main.helper.States;

import java.util.ArrayList;
import java.util.Scanner;

public class InputHandler {
    private ArrayList<Task> processes;
    private Scanner input;

    public InputHandler(){
        processes = new ArrayList<>();
        input = new Scanner(System.in);
    }
    public ArrayList<Task> getProcesses() {
        System.out.println("Enter processes properties. ");
        int id, priority, creationTime, requestedTime;
        do{
            System.out.print("Enter process ID: ");
            while(!input.hasNextInt()){
                System.out.print("Error! Enter process id as an integer: ");
                input.next();
            }
            id = input.nextInt();
            System.out.print("Enter process priority: ");
            while(!input.hasNextInt()){
                System.out.print("Error! Enter process priority as an integer: ");
                input.next();
            }
            priority = input.nextInt();
            System.out.print("Enter process creation time: ");
            while(!input.hasNextInt()){
                System.out.print("Error! Enter process creation time as an integer: ");
                input.next();
            }
            creationTime = input.nextInt();
            System.out.print("Enter process requested time: ");
            while(!input.hasNextInt()){
                System.out.print("Error! Enter process requested time as an integer: ");
                input.next();
            }
            requestedTime = input.nextInt();
            processes.add(new Task(id, priority, creationTime, requestedTime, States.WAITING));
            System.out.print("Do you want to continue? (y/n): ");
        }while(input.next().equals("y"));
        return processes;
    }
    public static ArrayList<Task> cloneInputProcesses(ArrayList<Task> processes){
        ArrayList<Task> processesCpy = new ArrayList<>();
        for(Task p : processes){
            processesCpy.add(new Task(p.getpId(),p.getPriority(),p.getCreationTime(),p.getRequestedTime(),p.getStates()));
        }
        return  processesCpy;
    }
}
