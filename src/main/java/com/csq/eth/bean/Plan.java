package com.csq.eth.bean;

public interface Plan {
    public void completePlan();
    public void beginPlan();
    public boolean ifFinish();
    public String toString();
    public void addLog(String log);
    public String getLog();
}
