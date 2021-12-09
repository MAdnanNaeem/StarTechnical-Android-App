package com.example.startechnical.AssisterClasses;

public class Finance {

    String  FoodExpence;
    String PetRollExpence;
    String AcessoriesExpence;
    String TotalAmount;
    String TaskProfit;
    String TaskNo;

    public Finance() {
    }

    public Finance(String foodExpence, String petRollExpence, String acessoriesExpence, String totalAmount, String taskProfit, String taskNo) {
        FoodExpence = foodExpence;
        PetRollExpence = petRollExpence;
        AcessoriesExpence = acessoriesExpence;
        TotalAmount = totalAmount;
        TaskProfit = taskProfit;
        TaskNo = taskNo;
    }

    public String getFoodExpence() {
        return FoodExpence;
    }

    public void setFoodExpence(String foodExpence) {
        FoodExpence = foodExpence;
    }

    public String getPetRollExpence() {
        return PetRollExpence;
    }

    public void setPetRollExpence(String petRollExpence) {
        PetRollExpence = petRollExpence;
    }

    public String getAcessoriesExpence() {
        return AcessoriesExpence;
    }

    public void setAcessoriesExpence(String acessoriesExpence) {
        AcessoriesExpence = acessoriesExpence;
    }

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getTaskProfit() {
        return TaskProfit;
    }

    public void setTaskProfit(String taskProfit) {
        TaskProfit = taskProfit;
    }

    public String getTaskNo() {
        return TaskNo;
    }

    public void setTaskNo(String taskNo) {
        TaskNo = taskNo;
    }
}
