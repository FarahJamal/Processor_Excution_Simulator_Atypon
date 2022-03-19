package model;

import java.util.ArrayList;

public class ReadyQueue {
    private ArrayList<Task> queue;

    public ReadyQueue()
    {
        queue = new ArrayList<>();
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

    public void enqueue(Task task)
    {
//the case when the queue is empty
      if (queue.isEmpty()) {
            queue.add(task);
        }
        else if(!this.contain(task)){
            int i;
            for (i = 0; i < queue.size(); i++) {
                if (queue.get(i).getPriority() > task.getPriority()) {
                    queue.add(i, task);
                    break;
                }
            }
// the case when the priority of the added process is higher than of all processes
            if(i == queue.size() ){
                queue.add(task);
            }
        }
    }

    private boolean contain(Task task){
        for(Task p : queue){
            if(p.getTaskID() == task.getTaskID())
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
