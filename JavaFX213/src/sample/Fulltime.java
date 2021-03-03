/**
 * Fulltime class is a subclass of Employee class and contains several different operations and data specific to a full-time employee. Fulltime class inherits some of its method from the Employee class and uses it to calculate payments, check if two full time employees are equal to one another along with returning annual salary for a fulltime employee. 
 * @author Manveer Singh, Prasish Sriram
 */
package sample;

public class Fulltime extends Employee {

    /**
     * Constructor that calls the constructor in Employee class and sets the
     * parameters fulltimeProfile and payRate
     *
     * @param fulltimeProfile basic data info of a fullTime Employee
     * @param payRate         amount that the employee gets paid (type double)
     *
     */
    public Fulltime(Profile fulltimeProfile, Double payRate) {

        super(fulltimeProfile, payRate);
    }

    /**
     * this method overrides the empty method in Employee class and calculates the
     * PayRate for full time employees specifically
     */
    @Override
    public void calculatePayment() {
        final int PAYPERIODS = 26;
        super.setPayments(super.getPayRate() / PAYPERIODS);
    }

    /**
     * calculates payments for management full time employees. Simply uses the
     * super. method to setPayments and calculate
     *
     * @param payment amount that we want to want to calculate (of type double)
     */
    public void calculatePayment(double payment) {
        super.setPayments(payment);
    }

    /**
     * getter method that invokes getProfile() and extracts employeeProfile
     *
     * @return the contents of getProfile() method in Employee class
     *         (employeeProfile)
     */
    public Profile getProfile() {
        return super.getProfile();
    }

    public double getPayRate(){
        return super.getPayRate();
    }

    /**
     * setter method that accepts a parameter of type double and assigns it to
     * setPayments in Employee profile (super class)
     *
     * @param payment the amount we want to set (of type double)
     */
    public void setPayments(double payment) {
        super.setPayments(payment);
    }

    /**
     * used for returning the object that it's applied on as a string object
     *
     * @return the payrate for a fulltime employee in the format described in
     *         project description
     */
    @Override
    public String toString() {
        return super.toString() + "FULL TIME::Annual Salary $" + super.getPayRate();
    }

    /**
     * Checks to see if 2 profiles are equal to one another
     *
     * @param obj of type Object that we want to check
     * @return true if the profiles are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Fulltime newFulltime;
        try {
            newFulltime = (Fulltime) obj;
        } catch (Exception e) {
            return false;
        }

        if (!super.getProfile().equals(newFulltime.getProfile())) {
            return false;
        }

        return true;
    }
}