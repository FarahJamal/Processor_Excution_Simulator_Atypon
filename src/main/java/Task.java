package main.java;
import main.helper.*;

public class Task {
    States states;
    int pId;
    int requestedTime;
    int priority;
    int creationTime;

    public Task() {
        this.pId = 0;
        this.requestedTime = 0;
        this.priority = 0;
        this.creationTime=0;
        this.states=States.WAITING;
    }

    public Task(int pId, int requestedTime, int priority, int creationTime, States states) {
        this.pId = pId;
        this.requestedTime = requestedTime;
        this.priority = priority;
        this.creationTime=creationTime;
        this.states=states;
    }

    public States getStates() {
        return states;
    }

    public void setStates(States states) {
        this.states = states;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getRequestedTime() {
        return requestedTime;
    }

    public void setRequestedTime(int requestedTime) {
        this.requestedTime = requestedTime;
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
    public void reduceTime(int time) {
        if(requestedTime >= time)
            requestedTime = requestedTime - time;
    }

    @Override
    public String toString() {
        return "Task{" +
                "states=" + states +
                ", pId=" + pId +
                ", requestedTime=" + requestedTime +
                ", priority=" + priority +
                ", creationTime=" + creationTime +
                '}';
    }
}
