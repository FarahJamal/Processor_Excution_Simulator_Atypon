package main.java;

public class GantRecord {
    int inTime;
    int outTime;
    int processId;

    public GantRecord() {
        inTime = 0;
        outTime = 0;
        processId = 0;
    }

    public GantRecord(int inTime, int outTime, int processId) {
        this.inTime = inTime;
        this.outTime = outTime;
        this.processId = processId;
    }

    public int getInTime() {
        return inTime;
    }

    public void setInTime(int inTime) {
        this.inTime = inTime;
    }

    public int getOutTime() {
        return outTime;
    }

    public void setOutTime(int outTime) {
        this.outTime = outTime;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    @Override
    public String toString() {
        return "GantRecord{" +
                "inTime=" + inTime +
                ", outTime=" + outTime +
                ", processId=" + processId +
                '}';
    }
}
