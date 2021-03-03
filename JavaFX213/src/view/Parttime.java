/**
 * Parttime class is a subclass of Employee class and contains several different operations and data specific to a part-time employee. The class takes care of different hourly rates for each part-time employee, overtime compensation and if an employee works more than 80 hours, they are paid 1.5 the payrate. 
 * Class also returns part time employee's compensation along with any extra pay, hours worked. 
 * @author Manveer Singh, Prasish Sriram
 */
package view;

public class Parttime extends Employee {

    private int hoursWorked; // Hours worked this period
    private double payRate; // Pay rate per hour

    /**
     * Constructor that calls the constructor in super class Emloyee and assigns
     * parttimeProfile and payRate along with initializing hoursWorked to 0.
     *
     * @param parttimeProfile common data for a part time employee
     * @param payRate         part time pay rate
     */
    public Parttime(Profile parttimeProfile, Double payRate) {
        super(parttimeProfile, payRate);
        this.hoursWorked = 0;
    }

    /**
     * setter method that initiliazes private instance variable hoursWorked to
     * hoursWorked (parameters)
     *
     * @param hoursWorked # of hours a part time employee worked
     */
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    /**
     * Overrides the calculatePayment() in Employee class and calculates the salary
     * for a part time employee. Max regular hours = 80 along with overtime
     * compensation of 1.5 set in this method
     */
    @Override
    public void calculatePayment() {
        final int MAX_REGULAR_HOURS = 80;
        final double OVERTIME_COMPENSATION = 1.5;
        if (hoursWorked <= MAX_REGULAR_HOURS) {

            super.setPayments(hoursWorked * super.getPayRate());
        } else {
            int overtimeHours = hoursWorked - MAX_REGULAR_HOURS;
            hoursWorked -= MAX_REGULAR_HOURS;

            super.setPayments(
                    (hoursWorked * super.getPayRate()) + (overtimeHours * super.getPayRate() * OVERTIME_COMPENSATION));
        }
    }

    /**
     * Allows us to return part employee profiles along with their compensation .
     * The method allows us to concatenate employee data along with hourly rate and
     * return it as a string object
     *
     * @return a string object consisting of part time employee hourly rate along
     *         with how many hours he/she worked per pay period
     */
    @Override
    public String toString() {
        return super.toString() + "PART TIME::Hourly Rate $" + super.getPayRate() + "::Hours worked this period: "
                + hoursWorked;
    }

    /**
     * checks to see if two part time profiles are equal to one another
     *
     * @return true if profiles match, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Parttime newParttime;
        try {
            newParttime = (Parttime) obj;
        } catch (Exception e) {

            return false;
        }

        if (!super.getProfile().equals(newParttime.getProfile())) {
            return false;
        }
        return true;
    }
}