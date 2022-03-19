package model;

import controller.States;

public class Task {
    private int taskID;
    private int priority;
    private int creationTime;
    private int requestedTime;
    private States states;

    public Task() {
        this.taskID = 0;
        this.priority = 0;
        this.creationTime = 0;
        this.requestedTime = 0;
        this.states=States.WAITING;
    }

    public States getStates() {
        return states;
    }

    public void setStates(States states) {
        this.states = states;
    }

    public Task(int processID, int priority, int creationTime, int requestedTime, States states) {
        this.taskID = processID;
        this.priority = priority;
        this.creationTime = creationTime;
        this.requestedTime = requestedTime;
        this.states=states;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(int creationTime) {
        this.creationTime = creationTime;
    }

    public int getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(int requestedTime) {
        this.requestedTime = requestedTime;
    }

    @Override
    public String toString() {
        return "{" +
                "processID = " + taskID +
                ", priority = " + priority +
                ", creationTime = " + creationTime +
                ", requestedTime = " + requestedTime +
                '}';
    }

    public void reduceTime(int time) {
        if(requestedTime >= time)
            requestedTime = requestedTime - time;
    }
}
