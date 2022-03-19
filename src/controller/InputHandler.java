package controller;

import model.Processor;
import model.Task;
import org.json.simple.*;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
public class InputHandler {
    private ArrayList<Task> tasks;
    private Scanner input;
Processor processor=new Processor();
    public InputHandler(){
        tasks = new ArrayList<>();
        input = new Scanner(System.in);
    }
    public ArrayList<Task> getProcesses() {

        System.out.println("Welcome to Processor Execution Simulator App");
        System.out.println("if you want to add input from file write F/f");
        System.out.println("if you want to add input manually write M/m");
        String inputType = input.nextLine().toLowerCase(Locale.ROOT);
        int processorId,id, priority, creationTime, requestedTime=0;

if(inputType.equals("m")) {
    System.out.println("Enter Processor Id:");
     processorId=input.nextInt();

    System.out.println("Enter processes properties. ");

    do {
        System.out.print("Enter process ID: ");
        while (!input.hasNextInt()) {
            System.out.print("Error! Enter process id as an integer: ");
            input.next();
        }
        id = input.nextInt();
        System.out.print("Enter process priority: ");
        while (!input.hasNextInt()) {
            System.out.print("Error! Enter process priority as an integer: ");
            input.next();
        }
        priority = input.nextInt();
        System.out.print("Enter process creation time: ");
        while (!input.hasNextInt()) {
            System.out.print("Error! Enter process creation time as an integer: ");
            input.next();
        }
        creationTime = input.nextInt();
        System.out.print("Enter process requested time: ");
        while (!input.hasNextInt()) {
            System.out.print("Error! Enter process requested time as an integer: ");
            input.next();
        }
        requestedTime = input.nextInt();
        tasks.add(new Task(id, priority, creationTime, requestedTime,States.WAITING));
        System.out.print("Do you want to continue? (y/n): ");
    } while (input.next().equals("y"));

    processor.setProcessorID(processorId);

}
else{
    JSONParser parser = new JSONParser();
    try {
        System.out.println("enter file name must be inside inputs folder :");
        Object obj = parser.parse(new FileReader("../../../inputs/"+input.nextLine()));
        JSONObject jsonObject = (JSONObject)obj;
        for (int j=1;j<=jsonObject.size();j++){
            JSONObject obj2 = (JSONObject)jsonObject.get("P"+j);
            processor.setProcessorID(j);
        for (int i=1;i<=obj2.size();i++)
        {

            JSONObject taskObj = (JSONObject)obj2.get("T"+i);
            priority=Integer.parseInt((String) taskObj.get("PRIORITY"));
            creationTime=Integer.parseInt((String) taskObj.get("CREATION_TIME"));
            requestedTime=Integer.parseInt((String) taskObj.get("REQUESTED_TIME"));
            id=Integer.parseInt((String) taskObj.get("pId"));
            tasks.add(new Task(id, priority, creationTime, requestedTime,States.WAITING));

        }

    }
    } catch(Exception e) {
        System.out.println(e.getMessage());
    }

}
processor.setTasks(tasks);
        return tasks;
    }
    public static ArrayList<Task> cloneInputProcesses(ArrayList<Task> tasks){
        ArrayList<Task> processesCpy = new ArrayList<>();
        for(Task p : tasks){
            processesCpy.add(new Task(p.getTaskID(),p.getPriority(),p.getCreationTime(),p.getRequestedTime(),p.getStates()));
        }
        return  processesCpy;
    }
}
