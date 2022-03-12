package main.java;

import java.util.ArrayList;

public class ReadyQueue {
    private ArrayList<Task> queue;

    public ReadyQueue() {
        queue=new ArrayList<>();
    }
    public Task dequeue()
    {
        Task p = null;
        if (!isEmpty())
        {
            p = queue.get(0);
            queue.remove(p);
        }
        return p;
    }

    public Task peek()
    {
        if(queue.isEmpty()){
            return null;
        }
        else{
            return queue.get(0);
        }
    }

    public void enqueue(Task process)
    {
//the case when the queue is empty
        if (queue.isEmpty()) {
            queue.add(process);
        }
        else if(!this.contain(process)){
            int i;
            for (i = 0; i < queue.size(); i++) {
                if (queue.get(i).getPriority() > process.getPriority()) {
                    queue.add(i,process);
                    break;
                }
            }
// the case when the priority of the added process is higher than of all processes
            if(i == queue.size() ){
                queue.add(process);
            }
        }
    }

    private boolean contain(Task process){
        for(Task p : queue){
            if(p.getpId() == process.getpId())
                return true;
            return false;
        }
        return false;
    }

    public int size()
    {
        return queue.size();
    }

    public Boolean isEmpty()
    {
        return (queue.size() == 0);
    }
}
