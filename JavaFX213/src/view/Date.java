/**
 * Date class that contains all of the valid information about date time frame(month/day/year). The class allows us to calculate if a certain date is in a leap year, valid/invalid formats, if the month to days information is valid along with a toString method that returns the date in MM/DD/YYYY format. One of the best uses of this class is the isValid() method that tests for leap years, invalid inputs, etc
 * @author Manveer Singh, Prasish Sriram
 */
package view;

import java.util.Calendar;

public class Date implements Comparable<Date> {

    private int month;
    private int day;
    private int year;

    /**
     * Constructor that allows us to split the date into months, days, and year
     * using the .split() and passing in our delimiter "/" token[0] is the
     * month...for example (01/02/2020.. 01 is extracted) token[1] is set to the
     * date passed. (01/02/2020..02 is extracted) token 2 is set to year. For
     * example (01/02/2020...2020 is extractd)
     *
     * @param date passed in by the user which later gets split into month, day and
     *             year. Initializes the instance variables month, day,year to the
     *             tokens of the String date broken up
     */
    public Date(String date) { // Creates date object from input String
        String[] tokens = date.split("/");
        this.month = Integer.valueOf(tokens[0]);
        this.day = Integer.valueOf(tokens[1]);
        this.year = Integer.valueOf(tokens[2]);
    }

    /**
     * Another constructor that returns today's date using the getInstance() method.
     * sets the private instance variables year, month and day to instances of
     * calendar
     */
    public Date() { // return today’s date
        Calendar calendar = Calendar.getInstance();

        this.year = calendar.get(calendar.YEAR);
        this.month = calendar.get(calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * getter method that returns the private instance variable year of class Date
     *
     * @return year, from class Date
     */
    public int getYear() {
        return this.year;
    }

    /**
     * getter method that returns the private instance variable month of class Date
     *
     * @return month, from class Date
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * getter method that retuns the private instance variable day of class Date
     *
     * @return day, from class date
     */
    public int getDay() {
        return this.day;
    }

    /**
     * method that compares the the object date of Date class (parameter) to the
     * instance variables of this class and returns -1 if year of date object is
     * greater than instance variable year, returns 1 it's less.
     *
     * Same for month and day, returns -1 if month/day is greater than their
     * respective instance variables, 1 otherwise
     *
     * @param date that gets passed in when called and used to compare the year,
     *             month, and day
     * @return -1 if year, month, day of date object is > than their respective
     *         instance variables. 1 if year,month, day of date object is < than
     *         their respective instance variables, 0 otherwise.
     */
    @Override
    public int compareTo(Date date) { // return 1, 0, or -1
        if (date.getYear() > this.year) {
            return -1;
        } else if (date.getYear() < this.year) {
            return 1;
        }

        if (date.getMonth() > this.month) {
            return -1;
        } else if (date.getMonth() < this.month) {
            return 1;
        }

        if (date.getDay() > this.day) {
            return -1;
        } else if (date.getDay() < this.day) {
            return 1;
        }

        return 0;
    }

    /**
     * method that checks to see if a date is valid. Base case of a year less than
     * 1900 or greater than this year (2021) is handled by the if statement followed
     * up with all the rules as mentioned in project 1 description. Date must be >=
     * 1900 and less than <= 2021. Specific months have ceratin amount of days,
     * february having 29 days in a leap year is also handled.
     *
     * @return true if the date is valid based on satisfaction, false otherwise.
     */
    public boolean isValid() {
        if (year < 1900 || year > 2021) {
            return false;
        }
        boolean isLeapYear = isLeapYear(year);
        Date today = new Date();
        if (this.compareTo(today) > 0) {
            return false;
        }

        if (month < 1 || month > 12) {
            return false;
        }
        if (month == 2) {
            if (isLeapYear) {
                if (day < 1 || day > 29) {
                    return false;
                }
            } else {
                if (day < 1 || day > 28) {
                    return false;
                }
            }
        }
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day < 1 || day > 31) {
                return false;
            }
        } else {
            if (day < 1 || day > 30) {
                return false;
            }
        }

        return true;
    }

    /**
     * method that determines if the year (parameter) is a leap year based on the
     * conditions: Step 1. If the year is evenly divisible by 4, go to step 2.
     * Otherwise, go to step 5. Step 2. If the year is evenly divisible by 100, go
     * to step 3. Otherwise, go to step 4. Step 3. If the year is evenly divisible
     * by 400, go to step 4. Otherwise, go to step 5. Step 4. The year is a leap
     * year. Step 5. The year is not a leap year.
     *
     * @param year that gets passed on when isLeapYear is invoked. Allows us to
     *             determine if the year is a leapYear
     * @return true if year is a leap year based on conditions specified, false
     *         otherwise.
     */
    private boolean isLeapYear(int year) {
        if (year < 1 || year > 9999) {
            return false;
        }
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * checks to see if the dates are equal
     *
     * @return true if dates are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        Date other;
        try {
            other = (Date) obj;
        } catch (Exception e) {
            return false;
        }

        if (this.compareTo(other) == 0) {
            return true;
        }
        return false;
    }

    /**
     * toString() method that overrides the original toString() method and allows to
     * initialize variables(mm,dd,yyyy) to values of private instane variables of
     * month,day, year using the valueOf method
     *
     * @return a string that concatenates the month, day and year together in
     *         mm/dd/yyyy format
     */
    @Override
    public String toString() {

        String mm = String.valueOf(month);
        String dd = String.valueOf(day);
        String yyyy = String.valueOf(year);

        return mm + "/" + dd + "/" + yyyy;
    }
}