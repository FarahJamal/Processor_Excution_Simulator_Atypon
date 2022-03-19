import controller.InputHandler;
import controller.Scheduling;
import controller.States;
import model.GanttRecord;
import model.Processor;
import model.Task;

import java.util.ArrayList;

public class ProcessorExecutionSimulator {

    public static void main(String[] args){
        InputHandler in = new InputHandler();
        Processor processor=new Processor();
        float avgWait = 0;
        float avgTurnAround = 0;


        ArrayList<Task> tasks = in.getProcesses();
        processor.setTasks(tasks);
        System.out.println(" all tasks size ===> "+processor.getTasks().size());
        ArrayList<Task> processesCpy = InputHandler.cloneInputProcesses(tasks);

        ArrayList<GanttRecord> gantt = new Scheduling().getGantt(tasks);
        //all processes
        System.out.println("processor id ==> "+processor.getProcessorID());
        System.out.println("List of all processes.");
        for(Task p : processesCpy){
            System.out.println(p.toString());
        }
        System.out.println();

        //gantt chart
        System.out.println("GANTT chart");
        for(int i = 0 ; i < gantt.size() ; i++){
            GanttRecord gR = gantt.get(i);
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
            int completionTime = Scheduling.getCompletionTime(p, gantt);
            System.out.println("P" + p.getTaskID()+": t = " + completionTime);
        }

        //turn around time
        System.out.println("Turn Around Time");
        for(Task p : processesCpy){
            int turnAroundTime = Scheduling.getTurnAroundTime(p, gantt);
            System.out.println("P" + p.getTaskID()+": t = " + turnAroundTime);
            avgTurnAround += turnAroundTime;
        }
        avgTurnAround = avgTurnAround / processesCpy.size();

        //waiting time
        System.out.println("Waiting Time");
        for(Task p : processesCpy){
            int waitingTime = Scheduling.getWaitingTime(p, gantt);
            System.out.println("P" + p.getTaskID()+": t = " + waitingTime);
            avgWait += waitingTime;
            p.setStates(States.COMPLETED);
        }
        avgWait = avgWait / processesCpy.size();

        System.out.println("Average Turn Around Time : " + avgTurnAround);
        System.out.println("Average Waiting Time : " + avgWait);

    }

}
