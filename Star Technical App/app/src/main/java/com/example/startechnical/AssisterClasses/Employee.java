package com.example.startechnical.AssisterClasses;

public class Employee {

    String Employee_Name;
    String  EmployeeCnic;
    String  EmployeeAgge;
    String EmployeeRoll;
    String EmployeeGender;
    String EmployeeSallary;
    String  EmployeeAdress;
    String  EmployeePhNo;

    public Employee() {
    }

    public Employee(String employeeName, String employeeCnic, String employeeAgge, String employeeRoll, String employeeGender, String employeeSallary, String employeeAdress, String employeePhNo) {
        Employee_Name = employeeName;
        EmployeeCnic = employeeCnic;
        EmployeeAgge = employeeAgge;
        EmployeeRoll = employeeRoll;
        EmployeeGender = employeeGender;
        EmployeeSallary = employeeSallary;
        EmployeeAdress = employeeAdress;
        EmployeePhNo = employeePhNo;
    }

    public String getEmployee_Name() {
        return Employee_Name;
    }

    public void setEmployee_Name(String employee_Name) {
        Employee_Name = employee_Name;
    }

    public String getEmployeeCnic() {
        return EmployeeCnic;
    }

    public void setEmployeeCnic(String employeeCnic) {
        EmployeeCnic = employeeCnic;
    }

    public String getEmployeeAgge() {
        return EmployeeAgge;
    }

    public void setEmployeeAgge(String employeeAgge) {
        EmployeeAgge = employeeAgge;
    }

    public String getEmployeeRoll() {
        return EmployeeRoll;
    }

    public void setEmployeeRoll(String employeeRoll) {
        EmployeeRoll = employeeRoll;
    }

    public String getEmployeeGender() {
        return EmployeeGender;
    }

    public void setEmployeeGender(String employeeGender) {
        EmployeeGender = employeeGender;
    }

    public String getEmployeeSallary() {
        return EmployeeSallary;
    }

    public void setEmployeeSallary(String employeeSallary) {
        EmployeeSallary = employeeSallary;
    }

    public String getEmployeeAdress() {
        return EmployeeAdress;
    }

    public void setEmployeeAdress(String employeeAdress) {
        EmployeeAdress = employeeAdress;
    }

    public String getEmployeePhNo() {
        return EmployeePhNo;
    }

    public void setEmployeePhNo(String employeePhNo) {
        EmployeePhNo = employeePhNo;
    }

}
