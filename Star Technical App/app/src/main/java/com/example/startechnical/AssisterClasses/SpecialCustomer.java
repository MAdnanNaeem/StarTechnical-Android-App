package com.example.startechnical.AssisterClasses;

public class SpecialCustomer {

    String CustomerName;
    String CustomerCnic;
    String CustomerNo;
    String CustomerPhNo;
    String TaskCompleated;
    String CustomerAdress;
    String purl;

    public SpecialCustomer() {
    }

    public SpecialCustomer(String customerName, String customerCnic, String customerNo, String customerPhNo, String taskCompleated, String customerAdress, String purl) {
        CustomerName = customerName;
        CustomerCnic = customerCnic;
        CustomerNo = customerNo;
        CustomerPhNo = customerPhNo;
        TaskCompleated = taskCompleated;
        CustomerAdress = customerAdress;
        this.purl = purl;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getCustomerCnic() {
        return CustomerCnic;
    }

    public void setCustomerCnic(String customerCnic) {
        CustomerCnic = customerCnic;
    }

    public String getCustomerNo() {
        return CustomerNo;
    }

    public void setCustomerNo(String customerNo) {
        CustomerNo = customerNo;
    }

    public String getCustomerPhNo() {
        return CustomerPhNo;
    }

    public void setCustomerPhNo(String customerPhNo) {
        CustomerPhNo = customerPhNo;
    }

    public String getTaskCompleated() {
        return TaskCompleated;
    }

    public void setTaskCompleated(String taskCompleated) {
        TaskCompleated = taskCompleated;
    }

    public String getCustomerAdress() {
        return CustomerAdress;
    }

    public void setCustomerAdress(String customerAdress) {
        CustomerAdress = customerAdress;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}