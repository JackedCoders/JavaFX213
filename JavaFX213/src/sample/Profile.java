/**
 * Profile contains the most generic information about an employee's
 * profile(name, department they work in, the date they were hired) along with a
 * method equals() to check if the profile exists already. Class also has a
 * toString() method that returns an employee's name, dateHired along with
 * department.
 *
 * @author Manveer Singh, Prasish Sriram
 */
package pkg;

public class Profile {
    private String name; // employee’s name in the form “lastname,firstname”
    private String department; // department code: CS, ECE, IT
    private Date dateHired; // date the employee was hired

    /**
     * constructor for class Profile that takes in 3 parameters and initializes the
     * parameters to private instance variables
     *
     * @param name       employee's name "Lastname, Firstname"
     * @param department CS, ECE, IT
     * @param dateHired  MM/DD/YYYY format
     */
    public Profile(String name, String department, Date dateHired) {
        this.name = name;
        this.department = department;
        this.dateHired = dateHired;
    }

    /**
     * getter method that returns the instance variable name of the class Profile
     * when called
     */
    public String getName() {
        return this.name;
    }

    /**
     * getter method that returns the instance variable department of the class
     * Profile when called
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     * getter method that returns the private instance variabe dateHired when called
     */
    public Date getDateHired() {
        return this.dateHired;
    }

    /**
     * method that overrides the toString() method in Employee class. returns a
     * String with instance variables name, department, and dateHired of class
     * Profile.
     */
    @Override
    public String toString() {

        return this.name + "::" + this.department + "::" + dateHired.toString() + "::";
    }

    /**
     * Checks to see if name, department, dateHires (attributes that constitute a
     * profile) are equal to one another
     *
     * @param obj obj we want to compare
     * @return true if the profiles (name, department, dateHired) are equal, false
     *         otherwise
     */
    @Override
    public boolean equals(Object obj) { // compare name, department and dateHired
        Profile newProfile;
        try {
            newProfile = (Profile) obj;
        } catch (Exception e) {
            return false;
        }
        if (!name.equals(newProfile.getName())) {
            return false;
        }
        if (!department.equals(newProfile.getDepartment())) {
            return false;
        }
        if (dateHired.compareTo(newProfile.getDateHired()) != 0) {
            return false;
        }

        return true;
    }
}