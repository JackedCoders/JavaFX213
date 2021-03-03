/**
 * Array based container class that holds that array emplist and implements the
 * entire employee database. The array emplist in this class contains instances
 * of full-time, part-time, and management. Company also provides a variety of
 * methods such as find() (to find a certain employee in the array and return ),
 * grow(initial capacity of of the container is 4, this method increases it by
 * 4, processPayments() (which process payments for all employees) and other
 * important programs like setHours, remove() etc. Company also controls the
 * printing of earning statements, print by department and print by date hired.
 *
 * @author Manveer Singh, Prasidh Sriram
 */
package view;

public class Company {
    private Employee[] emplist;
    private int numEmployees;

    /**
     * Constructor for Company class that initializes our private instances emplist
     * of type Employee to 4 and numEmployee of type int to 0
     */
    public Company() {
        this.emplist = new Employee[4];
        this.numEmployees = 0;
    }

    /**
     * getter method that returns the private instance variable numEmployees of
     * class Company when called in other classes
     *
     * @return returns the instance variable numEmployee in this class
     */
    public int getNumEmployees() {
        return this.numEmployees;
    }

    /**
     * Returns the index of the employee that we are trying to find in the empList
     * array of type Employee acts as an helper method for other methods to identify
     * certain employees
     *
     * @param employee
     * @return returns the index of employee parameter if found, returns -1
     *         otherwise
     */
    private int find(Employee employee) {

        for (int i = 0; i <= numEmployees; i++) {
            if (emplist[i] != null && emplist[i].equals(employee)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Grows the capacity of array to hold employees by 4 using the method grow()
     * below. Creating a new instance of type Employee and growing the capacity by
     * 4.
     */
    private void grow() {
        Employee[] newList = new Employee[emplist.length + 4];
        for (int i = 0; i < emplist.length; i++) {
            newList[i] = emplist[i];
        }
        this.emplist = newList;
    }

    /**
     * checks the profile of an employee before adding them. Calls the find() method
     * in this class, if employee already exists return false, otherwise checks to
     * see if private instance variable numEmployees is equal to the length of
     * emplist,if it does grow.
     *
     * @param employee object of class Employee.
     * @return returns true if employee added successfully, false if employee is
     *         already in list
     */
    public boolean add(Employee employee) {
        int index = find(employee);

        if (index != -1) {
            return false;
        }

        if (numEmployees+1 == emplist.length) {
            this.grow();
        }
        emplist[numEmployees + 1] = employee;
        numEmployees++;
        return true;

    }

    /**
     * removes a certain employee from the array empList based on user input. calls
     * the find() method in this class, if does not exist, return false,
     *
     * @param employee object of class Employee.
     * @return returns true if removed successfully, false if employee cannot be
     *         found
     */
    public boolean remove(Employee employee) {
        int index = find(employee);
        if (index == -1) {
            return false;
        }

        emplist[index] = null;
        for (int i = index; i < numEmployees; i++) {
            emplist[i] = emplist[i + 1];
        }
        numEmployees--;

        return true;
    }

    /**
     * Assigns working hours for a particular part-time employee
     *
     * @param employee object of class Employee. hoursWorked field will be assigned
     *                 here
     * @return false if setHours() fails, true if setHours() succeeds
     */
    public boolean setHours(Employee employee, int hoursWorked) {
        int index = find(employee);
        if (index == -1) {
            return false;
        }

        try {
            Parttime newParttime = (Parttime) emplist[index];
            newParttime.setHoursWorked(hoursWorked);

            emplist[index] = newParttime;
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * iterates through the array emplist and processes payments for all employees
     * based on their index. As long as the emplist[i] is satisfied and not null,
     * calculatePayment() is called on every employee in the array emplist of type
     * Employee
     */
    public void processPayments() {
        for (int i = 0; i <= numEmployees; i++) {

            if (emplist[i] != null) {

                if(emplist[i] instanceof Parttime) {
                    Parttime newParttime = (Parttime) emplist[i];
                    newParttime.calculatePayment();
                    emplist[i] = newParttime;

                }
                if(emplist[i] instanceof Fulltime){
                    Fulltime newFulltime = (Fulltime) emplist[i];
                    newFulltime.calculatePayment();
                    emplist[i] = newFulltime;

                }
                if(emplist[i] instanceof Management){
                    Management newManagement = (Management) emplist[i];
                    newManagement.calculatePayment();
                    emplist[i] = newManagement;

                }

            }

        }
    }

    /**
     * prints the contents (last name, firstname, department, earnings) of emplist
     * array of type Employee using the .toString() method.
     */
    public void print() {
        if (numEmployees == 0) {
            System.out.println("Employee database is empty.");
            return;
        }
        System.out.println("--Printing earning statements for all employees--");
        for (int i = 0; i <= numEmployees; i++) {
            if (emplist[i] != null) {
                System.out.println(emplist[i].toString());
            }
        }
    }

    /**
     * this method prints earning statements filtered by their respective
     * department. Using a simple for loop and a key set to index i of our emplist
     * array, we are able to compare the profile and the department using their
     * respective getter methods in a while loop. Finally iterating over emplist
     * again, as long as emplist[i] is not = null, we use the .toString method in
     * employee class to print the earnings statements
     */
    public void printByDepartment() {
        if (numEmployees == 0) {
            System.out.println("Employee database is empty.");
            return;
        }

        // Sort emplist by department
        for (int i = 1; i <= numEmployees; i++) {
            Employee key = emplist[i];
            int j = i - 1;

            while ((emplist[i] != null && emplist[j] != null) && j >= 0 && emplist[j].getProfile().getDepartment().compareTo(key.getProfile().getDepartment()) > 0) {
                emplist[j + 1] = emplist[j];
                j = j - 1;
            }
            emplist[j + 1] = key;
        }
        System.out.println("--Printing earning statements by department--");
        for (int i = 0; i <= numEmployees; i++) {
            if (emplist[i] != null) {
                System.out.println(emplist[i].toString());
            }
        }
    }

    /**
     * this method prints earning statements filtered by their respective hired
     * date. Base case of numEmployees equaling 0 is taken care of in the first if
     * statement then using the same approach as in printByDepartment, we are able
     * to use a key of type Employee to compare the profile and dateHired in the
     * while loop and then another for loop prints out the earnings
     */
    public void printByDate() {
        if (numEmployees == 0) {
            System.out.println("Employee database is empty.");
            return;
        }

        // Sort emplist by dateHired
        for (int i = 1; i < numEmployees; i++) {
            Employee key = emplist[i];
            int j = i - 1;

            while ((emplist[i] != null && emplist[j] != null) && j >= 0 && emplist[j].getProfile().getDateHired().compareTo(key.getProfile().getDateHired()) > 0) {
                emplist[j + 1] = emplist[j];
                j = j - 1;
            }
            emplist[j + 1] = key;
        }

        System.out.println("--Printing earning statements by date hired--");
        for (int i = 0; i <= numEmployees; i++) {
            if (emplist[i] != null) {
                System.out.println(emplist[i].toString());
            }
        }

    }

}