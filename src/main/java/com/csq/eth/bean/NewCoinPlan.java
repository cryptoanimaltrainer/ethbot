package com.csq.eth.bean;

public class NewCoinPlan implements Plan{
    private PlanDto planDto;
    public StringBuilder log = new StringBuilder();
    //0代表未执行，1代表执行中，2代表任务完成
    private Integer status = 0;

    public NewCoinPlan(PlanDto planDto) {
       this.planDto = planDto;
    }

    public PlanDto getPlanDto() {
        return planDto;
    }

    public void setPlanDto(PlanDto planDto) {
        this.planDto = planDto;
    }

    public String getLog() {
        return log.toString();
    }

    public void setLog(StringBuilder log) {
        this.log = log;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    @Override
    public void completePlan() {
        status = 2;
    }

    @Override
    public void beginPlan() {
        status = 1;
    }

    @Override
    public boolean ifFinish() {
        return status == 2;
    }
    @Override
    public void addLog(String log) {
        this.log.append(log+"\n");
    }

}
