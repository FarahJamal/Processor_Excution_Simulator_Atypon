package model;

import java.util.ArrayList;

public class Processor extends ArrayList<Task> {
    public  ArrayList<Task> tasks=new ArrayList<>();
    public  int processorID;

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getProcessorID() {
        return processorID;
    }

    public void setProcessorID(int processorID) {
        this.processorID = processorID;
    }
}
