package com.example.startechnical.AssisterClasses;

public class Task {
    String Task_Id;
    String AssignTo;
    String AssignDate;
    String AssignTime;
    String Task_Issue;
    String Bargain_Amount;
    String Feedback;
    String ClientName;
    String ClientId;
    String ClientAddress;
    String Client_cellno;
    String ClientType;

    public Task()
    {
    }

    public Task(String task_id, String assignTo, String assignDate, String assignTime, String task_issue, String bargainAmount, String feedback, String clientName, String clientId, String clientAddress, String client_cellno, String clientType) {
        Task_Id = task_id;
        AssignTo = assignTo;
        AssignDate = assignDate;
        AssignTime = assignTime;
        Task_Issue = task_issue;
        Bargain_Amount = bargainAmount;
        Feedback = feedback;
        ClientName = clientName;
        ClientId = clientId;
        ClientAddress = clientAddress;
        Client_cellno = client_cellno;
        ClientType = clientType;
    }

    public String getTask_Id() {
        return Task_Id;
    }

    public void setTask_Id(String task_Id) {
        Task_Id = task_Id;
    }

    public String getAssignTo() {
        return AssignTo;
    }

    public void setAssignTo(String assignTo) {
        AssignTo = assignTo;
    }

    public String getAssignDate() {
        return AssignDate;
    }

    public void setAssignDate(String assignDate) {
        AssignDate = assignDate;
    }

    public String getAssignTime() {
        return AssignTime;
    }

    public void setAssignTime(String assignTime) {
        AssignTime = assignTime;
    }

    public String getTask_Issue() {
        return Task_Issue;
    }

    public void setTask_Issue(String task_Issue) {
        Task_Issue = task_Issue;
    }

    public String getBargain_Amount() {
        return Bargain_Amount;
    }

    public void setBargain_Amount(String bargain_Amount) {
        Bargain_Amount = bargain_Amount;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getClientAddress() {
        return ClientAddress;
    }

    public void setClientAddress(String clientAddress) {
        ClientAddress = clientAddress;
    }

    public String getClient_cellno() {
        return Client_cellno;
    }

    public void setClient_cellno(String client_cellno) {
        Client_cellno = client_cellno;
    }

    public String getClientType() {
        return ClientType;
    }

    public void setClientType(String clientType) {
        ClientType = clientType;
    }
}