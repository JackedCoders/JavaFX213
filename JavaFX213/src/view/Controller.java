package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This class represents the controller Class for the Graphical User Interface that replaces Payroll Processing functionalities and adds
 * a lot more features and a smooth user experience. Includes functionality for add, remove, date picker, and import/
 * @author Manveer Singh, Prasidh Sriram
 */
public class Controller {

    private Company newCompany = new Company();

    @FXML
    private TextArea textArea, dataBaseArea;

    @FXML
    private TextField employeeName, annualSalary, hoursWorked, ratePerHour;

    @FXML
    private RadioButton fullTime, partTime, management;

    @FXML
    private RadioButton csDepartment, eceDepartment, itDepartment;

    @FXML
    private RadioButton managementTypeManager, managementTypeDepartmentHead, managementTypeDirector;

    @FXML
    private Button removeEmployee, addEmployee, setHours, clearInfo;

    @FXML
    private MenuButton fileDropDown, printDropDown, paymentDropDown;

    @FXML
    private MenuItem importFile,exportFile, printAll, printDepartment, printDateHired, calculatePayment ;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ToggleGroup departmentTypeGroup, employeeTypeGroup, managementTypeGroup;

    /**
     * Add Employee functionality - when the button is clicked along with appropriate input, this method
     * enters the information into our database and if any required fields are inappropriate or empty or our
     * database == empty, method returns an exception
     * @Param event: Action Event
     */
    @FXML
    void add(ActionEvent event){
        //First full time then part time then management code(selections) entry
        if(fullTime.isSelected()){
            try{
                String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                Date d = new Date(date);
                RadioButton deptSelected = (RadioButton) departmentTypeGroup.getSelectedToggle();
                String deptTypeText = deptSelected.getText();
                double salaryFullTime = Double.parseDouble(annualSalary.getText());
                Profile newProfileFtime = new Profile(employeeName.getText(),deptTypeText,d);
                Fulltime newFTime = new Fulltime(newProfileFtime,salaryFullTime);
                if(salaryFullTime <0){
                    textArea.appendText("Salary can not be negative!! Try again \n");
                }else if(newCompany.add(newFTime) == false){
                    textArea.appendText("Employee already exists!! \n");
                }else{
                    newCompany.add(newFTime);
                    textArea.appendText("Employee successfully added!! \n");
                }
            } catch (Exception e){
                textArea.appendText("Exception occurred: fields are missing \n");
            }
        }else if(partTime.isSelected()){
            try{
                String date1 = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                Date d = new Date(date1);
                RadioButton deptSelected = (RadioButton) departmentTypeGroup.getSelectedToggle();
                String deptTypeText = deptSelected.getText();
                double hourlyRate = Double.parseDouble(ratePerHour.getText());
                Profile newProfilePtime = new Profile(employeeName.getText(),deptTypeText,d);
                Parttime newPTime = new Parttime(newProfilePtime,hourlyRate);
                if(hourlyRate <0){
                    textArea.appendText("Rate can not be negative!! Try again \n");
                }else if(newCompany.add(newPTime) == false){
                    textArea.appendText("Employee already exists!! \n");
                }else{
                    newCompany.add(newPTime);
                    textArea.appendText("Employee successfully added!! \n");
                }
            }catch (Exception e){
                textArea.appendText("Exception occurred: fields are missing \n");
            }
        }

        else if(management.isSelected()){
            int managerTypeCode =0;
            try{
                String date1 = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                Date d = new Date(date1);
                double managementSalary = Double.parseDouble(annualSalary.getText());
                RadioButton deptSelected = (RadioButton) departmentTypeGroup.getSelectedToggle();
                String deptTypeText = deptSelected.getText();

                if(managementSalary <0){
                    textArea.appendText("Salary can not be negative!! Please try again \n");
                }else{
                    Profile newManagementProfile = new Profile(employeeName.getText(),deptTypeText,d);

                    if(managementTypeManager.isSelected()){

                        managerTypeCode =1;
                        Management newManagementEmp = new Management(newManagementProfile,managementSalary,managerTypeCode);
                        if(!newCompany.add(newManagementEmp)){
                            textArea.appendText("Employee already exists!! \n");
                        }else{
                            newCompany.add(newManagementEmp);
                            textArea.appendText("Employee successfully added!! \n");
                        }
                    }
                    else if(managementTypeDepartmentHead.isSelected()){
                        managerTypeCode =2;
                        Management newManagementEmp = new Management(newManagementProfile,managementSalary,managerTypeCode);
                        if(!newCompany.add(newManagementEmp)){
                            textArea.appendText("Employee already exists!! \n");
                        }else{
                            newCompany.add(newManagementEmp);
                            textArea.appendText("Employee successfully added!! \n");
                        }
                    }else if(managementTypeDirector.isSelected()){
                        managerTypeCode =3;
                        Management newManagementEmp = new Management(newManagementProfile,managementSalary,managerTypeCode);
                        if(!newCompany.add(newManagementEmp)){
                            textArea.appendText("Employee already exists!! \n");
                        }else{
                            newCompany.add(newManagementEmp);
                            textArea.appendText("Employee successfully added!! \n");
                        }
                    }
                }


            }catch (Exception e){
                textArea.appendText("Exception occurred: fields are missing \n");
            }
        }

        //Edge cases here
        if(datePicker.getValue() == null){
            textArea.appendText("Date is misssing!! Please choose an appropriate date \n");
        }
        if(partTime.isSelected() && ratePerHour.getText()== null){
            textArea.appendText("Rate per hour is missing! Please enter \n");
        }
        if(!csDepartment.isSelected() && !itDepartment.isSelected() && !eceDepartment.isSelected()){
            textArea.appendText("Department Allocation Missing. Select at least one \n");
        }
        if(partTime.isSelected() && hoursWorked.getText()== null){
            textArea.appendText("Hours worked is missing! Please retry \n");
        }
        if(!partTime.isSelected() && !fullTime.isSelected() && !management.isSelected()){
            textArea.appendText("Employee Status missing! Please retry \n");
        }


    }


    /**
     * Method that enforces hours worked are within the range of 0 to a 100, throw exceptions otherwise.
     * @param event
     */
    @FXML
    void validateHoursWorked(ActionEvent event){
        int hoursInput = Integer.parseInt(hoursWorked.getText());
        if(hoursInput <0){
            textArea.appendText("Hours worked can not be less than 0 \n");
        }else if(hoursInput > 100){
            textArea.appendText("Hours worked can not exceed 100. Be humane \n");
        }else{
            textArea.appendText("Hours worked are valid. Continue!!\n");
        }
    }

    /**
     * Method that makes sure that the date picked is valid using the isValid() from our Date class.
     * Any date after current date or less than 1900 is invalid along with certain leapYear and
     * other restrictions.
     * @param event
     */
    @FXML
    void validateDatePicker(ActionEvent event){

        String date1 = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Date d = new Date(date1);
        if(d.isValid()){
            textArea.appendText("The date "+ date1 + " is valid. \n");
        }else{
            textArea.appendText("The date "+ date1 + " is invalid. Pick today's or past date \n");
        }
    }

    /**
     * Method that removes employees from the database if found, if not, throws an error. Notice that
     * the user must pass in appropriate parameters to be able to remove an employee successfully
     * @param event
     */
    @FXML
    void remove(ActionEvent event){
        if(newCompany.getNumEmployees() ==0){
            textArea.appendText("No employees found to be removed! Database empty");
        }
        try{
            String date1 = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            Date d = new Date(date1);
            RadioButton deptSelected = (RadioButton) departmentTypeGroup.getSelectedToggle();
            String deptTypeText = deptSelected.getText();
            Profile removeEmployeeProfile = new Profile(employeeName.getText(), deptTypeText,d);
            Employee removeEmployee = new Employee(removeEmployeeProfile);
            Boolean testRemove = newCompany.remove(removeEmployee);

            if(testRemove){
                textArea.appendText("Employee has been removed successfully \n");
            } else{
                textArea.appendText("Employee does not exist! \n");
            }

        } catch (Exception e){
            textArea.appendText("Exception occurred - Please Try Again!\n");
        }

    }

    /**
     * Method that sets hours for part time employees. This method makes sure the hours we are trying to set
     * is a valid parameters and then sets it in our database.
     * @param event
     */
    @FXML
    void setHours(ActionEvent event){
        try{
            if(newCompany.getNumEmployees() ==0){
                textArea.appendText("Number of employees is 0. Database empty!! \n");
            }else{
                //Set the hours for parttime employees only..same as add but this time check for hours passed in
                String date1 = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                RadioButton deptSelected = (RadioButton) departmentTypeGroup.getSelectedToggle();
                String deptTypeText = deptSelected.getText();
                Date d = new Date(date1);
                int hoursInput = Integer.parseInt(hoursWorked.getText());
                Profile PtimeProfile = new Profile(employeeName.getText(), deptTypeText,d);
                Parttime PTime = new Parttime(PtimeProfile, 0.0);
                newCompany.setHours(PTime,hoursInput);
                if(hoursInput< 0){
                    textArea.appendText("Hours worked can not be negative \n");
                } else if(hoursInput > 100){
                    textArea.appendText("Hours worked exceeded max quantity. Try again \n");
                }
                if(newCompany.setHours(PTime, hoursInput)){
                    textArea.appendText(" \n Successful! Hours has been set for this employee \n");
                }
            }
        }catch(Exception e){
            textArea.appendText("Fields Missing! Try Again \n");
        }
    }


    /**
     * Method that resets every text field, radio button and our date picker for smoother user functionality.
     * Also useful for when a user makes a mistake inputting certain fields.
     * In case a field is disabled after clicking full time, part time, or
     * management, this button will allow you to reset it
     * @param event
     */
    @FXML
    void clearEverything(ActionEvent event){
        textArea.clear();
        employeeName.clear();
        annualSalary.clear();
        hoursWorked.clear();
        ratePerHour.clear();
        fullTime.setSelected(false);
        partTime.setSelected(false);
        management.setSelected(false);
        csDepartment.setSelected(false);
        eceDepartment.setSelected(false);
        itDepartment.setSelected(false);
        datePicker.getEditor().clear();
        managementTypeManager.setSelected(false);
        managementTypeDepartmentHead.setSelected(false);
        managementTypeDirector.setSelected(false);
        annualSalary.setEditable(true);
        hoursWorked.setEditable(true);
        managementTypeManager.setDisable(false);
        managementTypeDepartmentHead.setDisable(false);
        managementTypeDirector.setDisable(false);
        setHours.setDisable(false);
        ratePerHour.setEditable(true);
        ratePerHour.setDisable(false);
        hoursWorked.setEditable(true);
        hoursWorked.setDisable(false);

    }

    /**
     * Event handler method that fully disables non-applicable items
     * to futile. This method disables all functionality and features
     * and events for part time employee, makes sure you can not set hours
     * etc
     * @param event
     */
    @FXML
    void clickFullTimeButtonClear(ActionEvent event){
        if(fullTime.isSelected()){
            hoursWorked.clear();
            hoursWorked.setEditable(false);
            ratePerHour.clear();
            ratePerHour.setEditable(false);
            managementTypeDirector.setDisable(true);
            managementTypeDirector.setSelected(false);
            managementTypeManager.setDisable(true);
            managementTypeManager.setSelected(false);
            managementTypeDepartmentHead.setDisable(true);
            managementTypeDepartmentHead.setSelected(false);
            annualSalary.setEditable(true);
            setHours.setDisable(true);

        }
    }

    /**
     * Event handler method that fully disables all of the non-applicable
     * for Part time employees. This method makes sure the user is not able
     * to enter invalid data like annual salary, manager, department head &
     * director fields etc.
     * @param event
     */
    @FXML
    void clickPartTimeButtonClear(ActionEvent event){
        if(partTime.isSelected()){
            hoursWorked.setEditable(true);
            hoursWorked.setDisable(false);
            ratePerHour.setEditable(true);
            ratePerHour.clear();
            ratePerHour.setDisable(false);
            annualSalary.setEditable(false);
            annualSalary.clear();
            managementTypeDirector.setDisable(true);
            managementTypeDirector.setSelected(false);
            managementTypeManager.setDisable(true);
            managementTypeManager.setSelected(false);
            managementTypeDepartmentHead.setDisable(true);
            managementTypeDepartmentHead.setSelected(false);
            setHours.setDisable(false);
        }
    }

    /**
     * Event handler method that fully disables all of the non-applicable items for management type employees.
     * It also clears, and disables part time fields, makes sure the user inputs valid data.
     * This also enables the director, manager and department head radio buttons.
     * @param event
     */
    @FXML
    void clickManagementButtonClear(ActionEvent event){
        if(management.isSelected()){
            hoursWorked.setEditable(false);
            hoursWorked.setDisable(true);
            ratePerHour.setDisable(true);
            ratePerHour.setEditable(false);
            annualSalary.setEditable(true);
            setHours.setDisable(true);
            managementTypeDirector.setDisable(false);
            managementTypeManager.setDisable(false);
            managementTypeDepartmentHead.setDisable(false);
            ratePerHour.clear();
            hoursWorked.clear();
        }
    }

    /**
     * Method that prints all earning statements for every employee in our databases.
     * Notice that this method uses the print() method in Company class which returns
     * as String instead of a System.out.println.
     * @param event
     */
    @FXML
    void printAllEmployees(ActionEvent event){
        dataBaseArea.clear();
        String[] output = newCompany.print();
        for(int i =0; i < output.length;i++){
            dataBaseArea.appendText(output[i] + "\n");
        }
    }

    /**
     * Method that prints employees by their date hired (in order from January -> february -> march etc).
     * Simply appends the output to our text area. Notice that this method uses the printByDate() method in Company class
     * which return as String instead of a System.out.println.
     * @param event
     */
    @FXML
    void printByDateHired(ActionEvent event){
        dataBaseArea.clear();
        String[] output = newCompany.printByDate();
        for(int i =0; i < output.length;i++){
            dataBaseArea.appendText(output[i] + "\n");
        }
    }

    /**
     * Method that prints employees by their department (ECE, IT, CS) and sorts them together and prints.
     * Simply appends the output to our text area. Notice that this method uses the printByDepartment() method in Company class
     * which return as String instead of a System.out.println.
     * @param event
     */
    @FXML
    void printByDepartment(ActionEvent event){
        dataBaseArea.clear();
        String[] output = newCompany.printByDepartment();
        for(int i =0; i < output.length;i++){
            dataBaseArea.appendText(output[i] + "\n");
        }
    }


    /**
     * Method that calculates payments for all types of employee types (Full time, parttime, management)
     * based on their hoursWorked and pay rate(note this is exclusive for part time employees.)
     * @param event
     */
    @FXML
    void calculatePayment(ActionEvent event){
        dataBaseArea.clear();
        newCompany.processPayments();
        dataBaseArea.appendText("Payments have been calculated successfully \n");
    }

    /**
     * Method that allows the user to import database (.txt) files and store them in the database.
     * This method also determines whether the employee being added is a parttime, full time or management
     * level employee
     * @param event
     */
    @FXML
    void importFile(ActionEvent event) throws IOException{
        FileChooser c = new FileChooser();
        c.setTitle("Open Source File for the Import");
        c.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"), new ExtensionFilter("All Files", "*.*"));
        Stage s = new Stage();
        File srcFile = c.showOpenDialog(s);
        Scanner scanner = new Scanner(srcFile);




    }

    /**
     * Method that allows the user to export the information in the database to a .txt file.
     * The user can choose where they want to save the file, really user friendly and allows for
     * manual storage of the database.
     * @param event
     */
    @FXML
    void export(ActionEvent event){
        FileChooser c = new FileChooser();
        c.setTitle("Open Target File for the Export");
        c.getExtensionFilters().addAll(new ExtensionFilter("Text Files","*.txt"), new ExtensionFilter("All Files","*.*"));
        Stage s = new Stage();
        File srcFile = c.showSaveDialog(s);
        //try and catch to catch exceptions when exporting
        try{
            String[] tokens = newCompany.exportDatabase(); //get the array of string from company class
            FileWriter w = new FileWriter(srcFile.getAbsoluteFile());
            for(int i =0; i <tokens.length; i++){
                w.write(tokens[i] + "\n");
            }
            w.close();
        }catch (Exception e){
            dataBaseArea.appendText("Invalid path! Exception occurred. Please try again!");
        }
    }


}
