/**
 * * This class represents the controller Class for the Graphical User Interface that replaces Payroll Processing functionalities and adds
 *  * a lot more features and a smooth user experience. Includes functionality for add, remove, date picker, and import/export
 * @author Manveer Singh, Prasidh Sriram
 */
package view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Scanner;
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
    private ToggleGroup departmentTypeGroup, employeeTypeGroup;

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
                String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM/DD/YYYY"));
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
                    textArea.appendText(newFTime.toString());
                }
            } catch (Exception e){
                textArea.appendText("Exception occurred: fields are missing \n");
            }
        }else if(partTime.isSelected()){
            try{
                String date1 = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM/DD/YYYY"));
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
                    textArea.appendText(newPTime.toString());
                }
            }catch (Exception e){
                textArea.appendText("Exception occurred: fields are missing \n");
            }
        }

        else if(management.isSelected()){
            int managerTypeCode =0;
            try{
                String date1 = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM/DD/YYYY"));
                Date d = new Date(date1);
                double managementSalary = Double.parseDouble(annualSalary.getText());
                RadioButton deptSelected = (RadioButton) departmentTypeGroup.getSelectedToggle();
                String deptTypeText = deptSelected.getText();

                if(managementSalary <0){
                    textArea.appendText("Salary can not be negative!! Please try again \n");
                }else{
                    Profile newManagementProfile = new Profile(employeeName.getText(),deptTypeText,d);
                    Management newManagementEmp = new Management(newManagementProfile,managementSalary,managerTypeCode);
                    if(managementTypeManager.isSelected()){
                        managerTypeCode =1;
                        if(newCompany.add(newManagementEmp) == false){
                            textArea.appendText("Employee already exists!! \n");
                        }else{
                            newCompany.add(newManagementEmp);
                            textArea.appendText(newManagementEmp.toString());
                        }
                    }
                    else if(managementTypeDepartmentHead.isSelected()){
                        managerTypeCode =2;
                        if(newCompany.add(newManagementEmp) == false){
                            textArea.appendText("Employee already exists!! \n");
                        }else{
                            newCompany.add(newManagementEmp);
                            textArea.appendText(newManagementEmp.toString());
                        }
                    }else if(managementTypeDirector.isSelected()){
                        managerTypeCode =3;
                        if(newCompany.add(newManagementEmp) == false){
                            textArea.appendText("Employee already exists!! \n");
                        }else{
                            newCompany.add(newManagementEmp);
                            textArea.appendText(newManagementEmp.toString());
                        }
                    }
                }


            }catch (Exception e){
                textArea.appendText("Exception occurred: fields are missing \n");
            }
        }
    }

    void setHours(ActionEvent event){
        try{
            if(newCompany.getNumEmployees() ==0){
                textArea.appendText("Number of employees is 0. Database empty!! \n");
            }else{
                //Set the hours for partime employees only..same as add but this time check for hours passed in
                String date1 = datePicker.getValue().format(DateTimeFormatter.ofPattern("MM/DD/YYYY"));
                Date d = new Date(date1);
                double hoursInput = Double.parseDouble(hoursWorked.getText());

            }
        }catch(Exception e){
            textArea.appendText("Field Missing : Hours Worked. Try again!! \n");
        }
    }

}