/**
 * Employee class defines the most common data(such as payRate, payments and employeeProfile of type Profile) and operations(getters, setters, calculating payment for each employee based on their position - full time, part time, management etc) along with checking if the employee is the same based on their date for all employee type. This class also handles printing payments for each employeeProfile
 *
 * @author Manveer Singh, Prasish Sriram
 */

package sample;

import java.text.*;

public class Employee {

    private Profile employeeProfile;
    private Double payRate;
    private Double payments;

    /**
     * getter method that returns the private instance variable emloyeeProfile of
     * this class
     *
     * @return employeeProfile (instance private variable)
     */
    public Profile getProfile() {
        return this.employeeProfile;
    }

    /**
     * setter method that initiliazes the private instance employeeProfile to the
     * parameter profile being passed when this method gets invoked
     *
     * @param profile of type Profile
     */
    public void setProfile(Profile profile) {
        this.employeeProfile = profile;
    }

    /**
     * getter method that returns the private instance payments when invoked
     *
     * @return payments (private instance variable)
     */
    public double getPayments() {
        return this.payments;
    }

    /**
     * setter method that initializes the instance variable payments to the
     * parameter payments when invoked
     *
     * @param payments of type double
     */
    public void setPayments(double payments) {
        this.payments = payments;
    }

    /**
     * getter method that returns private instance variable payRate of class
     * Employee
     *
     * @return payRate
     */
    public double getPayRate() {
        return this.payRate;
    }

    /**
     * setter method that initializes the parameter payRate to the instance variable
     * in Employee class
     *
     * @param payRate value of type double that gets passed in as a parameter to
     *                this method and allows us to initiaize it to instance variable
     *                payRate of this class
     */
    public void setPayRate(double payRate) {
        this.payRate = payRate;
    }

    /**
     * empty method that gets overriden by all of the subclasses (partime, fulltime,
     * & management)
     */
    public void calculatePayment() {

    }

    /**
     * Contructor that initialiazes private instance variables employeeProfile,
     * payRate to parameters employeeProfile of type Profile, payRate of type double
     * and sets payments to 0 (typcasting to double)
     *
     * @param employeeProfile that gets passed when invoked
     * @param payRate         that gets passed when invoked
     */
    public Employee(Profile employeeProfile, Double payRate) {
        this.employeeProfile = employeeProfile;
        this.payRate = payRate;
        this.payments = (double) 0;
    }

    /**
     * Default constructor for the class Employee
     */
    public Employee() {

    }

    /**
     * toString method that allows us to print payments in the desired way according
     * to the description
     *
     * @return payment for each employeeProfile (Concatenation to match the
     *         description of the project)
     */
    @Override
    public String toString() {
        NumberFormat numberFormat = new DecimalFormat("###,###,###.00");
        String paymentStr = numberFormat.format(payments);

        return employeeProfile.toString() + "Payment $" + paymentStr + "::";
    }

    /**
     * Checks to see if the employees are equal
     *
     * @param obj of type Object
     * @return true if profiles are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Employee newEmployee;
        try {
            newEmployee = (Employee) obj;
        } catch (Exception e) {
            return false;
        }

        if (!employeeProfile.equals(newEmployee.getProfile())) {
            return false;
        }
        return true;
    }
}