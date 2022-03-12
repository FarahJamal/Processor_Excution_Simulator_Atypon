package main.java;

import main.helper.States;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        InputHandler inputHandler=new InputHandler();
        float avgWait = 0;
        float avgTurnAround = 0;
        ArrayList<Task> processes = inputHandler.getProcesses();

        ArrayList<Task> processesCpy = InputHandler.cloneInputProcesses(processes);
        ArrayList<GantRecord> gantt = new TasksScheduling().getGantt(processes);

        //all processes
        System.out.println("List of all processes.");
        for(Task p : processesCpy){
            System.out.println(p.toString());
        }
        System.out.println();

        //gantt chart
        System.out.println("GANTT chart");
        for(int i = 0 ; i < gantt.size() ; i++){
            GantRecord gR = gantt.get(i);
            if(i == 0)
                System.out.print(gR.toString());
            else
                System.out.print(" --P" + gR.getProcessId() + "-- |" + gR.getOutTime() +"|");
        }
        System.out.println();
        System.out.println();

        //completion time
        System.out.println("Completion Time");
        for(Task p : processesCpy){
            int completionTime = TasksScheduling.getCompletionTime(p, gantt);
            System.out.println("P" + p.getpId()+": t = " + completionTime);
        }

        //turn around time
        // waiting time+requested time
        // the whole waiting time
        System.out.println("Turn Around Time");
        for(Task p : processesCpy){
            int turnAroundTime = TasksScheduling.getTurnAroundTime(p, gantt);
            System.out.println("P" + p.getpId()+": t = " + turnAroundTime);
            avgTurnAround += turnAroundTime;
        }
        avgTurnAround = avgTurnAround / processesCpy.size();

        //waiting time
        System.out.println("Waiting Time");
        for(Task p : processesCpy){
            int waitingTime = TasksScheduling.getWaitingTime(p, gantt);
            System.out.println("P" + p.getpId()+": t = " + waitingTime);
            avgWait += waitingTime;
        }
        avgWait = avgWait / processesCpy.size();

        System.out.println("Average Turn Around Time : " + avgTurnAround);
        System.out.println("Average Waiting Time : " + avgWait);

    }

    }

