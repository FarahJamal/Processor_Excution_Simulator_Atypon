package controller;

import model.GanttRecord;
import model.Task;
import model.ReadyQueue;

import java.util.ArrayList;

public class Scheduling {

    private ArrayList<GanttRecord> gantt;
    private int currentTime;
    private ReadyQueue readyQueue;

    public Scheduling(){
        gantt = new ArrayList<>();
        currentTime = 0;
        readyQueue = new ReadyQueue();
    }

    public ArrayList<GanttRecord> getGantt(ArrayList<Task> tasks){
        // initialization of creation times
        currentTime = this.getFirstCreationTime(tasks);
        int in ,out = currentTime;

        // the first processes in the queue are ready
        ArrayList<Task> processes1 = this.getAllCreatedProcesses(tasks);

        for(Task task : processes1){
            // the increase in the queue is almost on the basis of priority
            readyQueue.enqueue(task);
            tasks.remove(task);
        }

        // the remaining processes are listed depending on the creation time
        ArrayList<Task> orderedByArrivingTime = this.orderProcessesByCreationTime(tasks);

        // as long as the queue is not almost empty
        while(!readyQueue.isEmpty()){
            // deals with the process with the highest priority from the turn ready
            Task task = readyQueue.dequeue();


            // Two cases to consider, the first is when we have new processes coming and the other case
            // is when new processes do not come but those that are ready are treated
            if(orderedByArrivingTime.size() > 0) {
                //Handle of all arriving processes while one process has the control of the Processor
                for (int i = 0; i < orderedByArrivingTime.size(); i++) {
                    Task p = orderedByArrivingTime.get(i);
                    // the new process that comes when the Processor is busy compares the priority and if the priority is with
                    // decreases that the priority of the process that has control then the new process is added to the ready queue
                    if (p.getCreationTime() >= task.getCreationTime()
                            && p.getCreationTime() < (task.getRequestedTime() + currentTime)
                            && p.getPriority() >= task.getPriority()) {
                        readyQueue.enqueue(p);
                        orderedByArrivingTime.remove(p);
                        i--;
                    }
                    // the new process that comes when the Processor is busy compares the priority and if the priority is with
                    // greater than the priority of the process that has control then the old process is added to the ready queue
                    // with my requested time reduced, Processor control is taken over by the new process
                     else if (p.getCreationTime() >= task.getCreationTime()
                            && p.getCreationTime() < (task.getRequestedTime() + currentTime)
                            && p.getPriority() < task.getPriority()) {
                        in = currentTime;
                        currentTime = p.getCreationTime();
                        task.reduceTime(currentTime - in);
                        out = currentTime;
                        readyQueue.enqueue(task);
                        GanttRecord gR = new GanttRecord(in, out, task.getTaskID());
                        gantt.add(gR);

                        readyQueue.enqueue(p);
                        orderedByArrivingTime.remove(p);
                        i--;

                        break;
                    }
                     // if you check the entire list of new processes coming up and none of them are valid
                    // to take control of the Processor, the process that has the control continues with the time it needs
                    if (i == orderedByArrivingTime.size() - 1) {
                        in = currentTime;
                        currentTime += task.getRequestedTime();
                        out = currentTime;
                        gantt.add(new GanttRecord(in, out, task.getTaskID()));
                        // checks if at the end of the uninterrupted execution of a process we have a new process that
                        // comes and goes in the ready row
                        if(orderedByArrivingTime.size() > 0
                                && readyQueue.isEmpty()) {
                            readyQueue.enqueue(orderedByArrivingTime.get(0));
                        }
                    }
                }
            }
            // the other case when we do not have new processes that follow but only those that are in the queue are treated

            else{
                in = currentTime;
                currentTime += task.getRequestedTime();
                out = currentTime;
                gantt.add(new GanttRecord(in, out, task.getTaskID()));
            }
        }
        return gantt;
    }

    public static int getCompletionTime(Task p, ArrayList<GanttRecord> gantt) {
        int completionTime = 0;
        for(GanttRecord gR : gantt){
            if(gR.getProcessId() == p.getTaskID())
                completionTime = gR.getOutTime();
            p.setStates(States.EXECUTING);
        }
        return completionTime;
    }

    public static int getTurnAroundTime(Task p, ArrayList<GanttRecord> gantt) {
        int completionTime = Scheduling.getCompletionTime(p,gantt);
        return completionTime-p.getCreationTime();
    }

    public static int getWaitingTime(Task p, ArrayList<GanttRecord> gantt) {
        int turnAroundTime = Scheduling.getTurnAroundTime(p,gantt);
        return turnAroundTime-p.getRequestedTime();
    }

    private ArrayList<Task> orderProcessesByCreationTime(ArrayList<Task> tasks){
        ArrayList<Task> newTasks = new ArrayList<>();
        while(tasks.size() != 0) {
            Task p = this.getFirstCreatedProcess(tasks);
            tasks.remove(p);
            newTasks.add(p);
        }
        return newTasks;
    }

    private Task getFirstCreatedProcess(ArrayList<Task> tasks){
        int min = Integer.MAX_VALUE;
        Task task = null;
        for(Task p : tasks){
            if(p.getCreationTime() < min){
                min = p.getCreationTime();
                task = p;
            }
        }
        return task;
    }

    private ArrayList<Task> getAllCreatedProcesses(ArrayList<Task> tasks){
        int min = this.getFirstCreationTime(tasks);
        ArrayList<Task> processes1 = new ArrayList<>();
        for(Task p : tasks){
            if(p.getCreationTime() == min){
                processes1.add(p);
            }
        }
        return processes1;
    }

    private int getFirstCreationTime(ArrayList<Task> tasks){
        int min = Integer.MAX_VALUE;
        for(Task p : tasks){
            if(p.getCreationTime() < min){
                min = p.getCreationTime();
            }
        }
        return min;
    }

}