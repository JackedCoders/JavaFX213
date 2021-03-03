/**
 * Management class is a subclass of Employee and contains specific data and operations exclusive to full time employees with different management roles. A full time emnployee has 3 different management roles (manager, department head, and director). In this class, we calculate compensation for full time employees based on their management role. This class also contains a toString method that prints the salary. Class also inherits some of its methods from Employee class
 * @author Manveer Singh, Prasish Sriram
 */

package pkg;

public class Management extends Fulltime {

    private int managementCode;
    private Double managerCompensation;

    /**
     * Constructor method that allows for proper calculation of fullTime employee
     * based on their management roles. Constructor also calls the constructor in
     * superclass Employee and initializes values. Also initializes private instance
     * variable managementCode to the parameter (managerType) being passed in
     *
     * @param managerProfile
     * @param payRate
     * @param managerType    either 1 (Manager),2(Department head) or 3 (director).
     *                       this parameters allows us to properly assign bonuses
     *
     */
    public Management(Profile managerProfile, Double payRate, int managerType) {

        super(managerProfile, payRate);
        this.managementCode = managerType;

        int payPeriods = 26; // # of biweekly payment periods per year
        int managerBonus = 5000;
        int departmentHeadBonus = 9500;
        int directorBonus = 12000;

        if (managerType == 1) {
            this.managerCompensation = (double) managerBonus / payPeriods;
        } else if (managerType == 2) {
            this.managerCompensation = (double) departmentHeadBonus / payPeriods;
        } else {
            this.managerCompensation = (double) directorBonus / payPeriods;
        }
    }

    /**
     * getter method that returns the private instance variable managerCompensation
     * when invoked
     *
     * @return the amount a certain fulltime employee gets paid based on their
     *         management role
     */
    public double getManagerCompensation() {
        return this.managerCompensation;
    }

    /**
     * Overrides the calculatePayment() in employee class and calculates the payment
     * for an full time employee based on 26 pay periods
     */
    @Override
    public void calculatePayment() {
        final int PAY_PERIODS = 26; // # of biweekly payment periods per year
        super.calculatePayment(super.getPayRate() / PAY_PERIODS + managerCompensation);
    }

    /**
     * Allows us to return full employee profiles along with their compensation
     * based on their management code(1,2 or 3). The method allows us to concatenate
     * employee data along with managerCompensation and return it as a string object
     *
     * @return string object containing employeeData and compensation based on
     *         management code (1, 2 or 3)
     */
    @Override
    public String toString() {
        if (managementCode == 1) {
            return super.toString() + "::Manager Compensation $" + managerCompensation;
        }
        if (managementCode == 2) {
            return super.toString() + "::DepartmentHead Compensation $" + managerCompensation;
        }
        if (managementCode == 3) {
            return super.toString() + "::Director Compensation $" + managerCompensation;
        }
        return null; // return for compilation purposes
    }

    /**
     * checks to see if management profiles are equal to one another
     *
     * @param obj what we want to compare
     * @return true if management profile already exists, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Management newManagement;
        try {
            newManagement = (Management) obj;
        } catch (Exception e) {
            return false;
        }

        if (!super.getProfile().equals(newManagement.getProfile())) {
            return false;
        }

        return true;
    }
}