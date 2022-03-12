package main.java;

import main.helper.States;

import java.util.ArrayList;

public class TasksScheduling {
    private ArrayList<GantRecord> gantt;
    private int currentTime;
    private int excutionTime;
    private ReadyQueue readyQueue;

    public TasksScheduling(){
        gantt = new ArrayList<>();
        currentTime = 0;
        excutionTime = 0;
        readyQueue = new ReadyQueue();
    }

    public ArrayList<GantRecord> getGantt(ArrayList<Task> processes){
        currentTime = this.getFirstArrivingTime(processes);
        int in = currentTime ,out = currentTime;
        ArrayList<Task> processes1 = this.getFirstArrivingProcesses(processes);

        for(Task process : processes1){
            readyQueue.enqueue(process);
            processes.remove(process);
        }

        ArrayList<Task> orderedByArrivingTime = this.orderProcessesByArrivingTime(processes);

        while(!readyQueue.isEmpty()){
            Task process = readyQueue.dequeue();

     if(orderedByArrivingTime.size() > 0) {
                for (int i = 0; i < orderedByArrivingTime.size(); i++) {
                    Task p = orderedByArrivingTime.get(i);

                    if (p.getCreationTime() >= process.getCreationTime()
                            && p.getCreationTime() < (process.getRequestedTime() + currentTime)
                            && p.getPriority() >= process.getPriority()) {
                        readyQueue.enqueue(p);
                        orderedByArrivingTime.remove(p);
                        i--;
                    }

                    else if (p.getCreationTime() >= process.getCreationTime()
                            && p.getCreationTime() < (process.getRequestedTime() + currentTime)
                            && p.getPriority() < process.getPriority()) {
                        in = currentTime;
                        currentTime = p.getCreationTime();
                        process.reduceTime(currentTime - in);
                        out = currentTime;
                        readyQueue.enqueue(process);
                        GantRecord gR = new GantRecord(in, out, process.getpId());
                        gantt.add(gR);
process.setStates(States.EXECUTING);
                        readyQueue.enqueue(p);
                        orderedByArrivingTime.remove(p);
                        i--;

                        break;
                    }

                    if (i == orderedByArrivingTime.size() - 1) {
                        in = currentTime;
                        currentTime += process.getRequestedTime();
                        out = currentTime;
                        gantt.add(new GantRecord(in, out, process.getpId()));

                        if(orderedByArrivingTime.size() > 0
                                && readyQueue.isEmpty()) {
                            readyQueue.enqueue(orderedByArrivingTime.get(0));
                        }
                    }
                }
            }
            else{
                in = currentTime;
                currentTime += process.getRequestedTime();
                out = currentTime;
                gantt.add(new GantRecord(in, out, process.getpId()));
            }
        }
        return gantt;
    }

    public static int getCompletionTime(Task p, ArrayList<GantRecord> gantt) {
        int completionTime = 0;
        for(GantRecord gR : gantt){
            if(gR.getProcessId() == p.getpId())
                completionTime = gR.getOutTime();
            p.setStates(States.COMPLETED);
        }
        return completionTime;
    }

    public static int getTurnAroundTime(Task p, ArrayList<GantRecord> gantt) {
        int completionTime = TasksScheduling.getCompletionTime(p,gantt);
        return completionTime-p.getCreationTime();
    }

    public static int getWaitingTime(Task p, ArrayList<GantRecord> gantt) {
        int turnAroundTime = TasksScheduling.getTurnAroundTime(p,gantt);
        return turnAroundTime-p.getRequestedTime();
    }

    private ArrayList<Task> orderProcessesByArrivingTime(ArrayList<Task> processes){
        ArrayList<Task> newProcesses = new ArrayList<>();
        while(processes.size() != 0) {
            Task p = this.getFirstArrivingProcess(processes);
            processes.remove(p);
            newProcesses.add(p);
        }
        return newProcesses;
    }

    private Task getFirstArrivingProcess(ArrayList<Task> processes){
        int min = Integer.MAX_VALUE;
        Task process = null;
        for(Task p : processes){
            if(p.getCreationTime() < min){
                min = p.getCreationTime();
                process = p;
            }
        }
        return process;
    }

    private ArrayList<Task> getFirstArrivingProcesses(ArrayList<Task> processes){
        int min = this.getFirstArrivingTime(processes);
        ArrayList<Task> processes1 = new ArrayList<>();
        for(Task p : processes){
            if(p.getCreationTime() == min){
                processes1.add(p);
            }
        }
        return processes1;
    }

    private int getFirstArrivingTime(ArrayList<Task> processes){
        int min = Integer.MAX_VALUE;
        for(Task p : processes){
            if(p.getCreationTime() < min){
                min = p.getCreationTime();
            }
        }
        return min;
    }
}
