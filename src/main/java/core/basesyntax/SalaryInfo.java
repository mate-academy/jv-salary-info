package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SalaryInfo {
    private static final int quantityOfSubstrings = 4;
    private Date dateFrom;
    private Date dateTo;
    private SimpleDateFormat format;
    private ArrayList<EmployeeData> employeeData;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        checkOfValidateData(names, data);
        String salaryInfo = "";
        salaryInfo += getSalaryPeriod(dateFrom, dateTo);
        parsingData(data, names);
        for (String name : names) {
            int res = 0;
            for (EmployeeData employeeDatum : employeeData) {
                if (name.equals(employeeDatum.name)) {
                    res += employeeDatum.hours * employeeDatum.income;
                }
            }
            salaryInfo += "\n" + name + " - " + res;
        }
        return salaryInfo;
    }

    public void checkOfValidateData(String [] names, String[] data) {
        if (names == null) {
            throw new WrongDataException("Wrong names");
        }
        if (data == null) {
            throw new WrongDataException("Wrong data");
        }
    }

    public String getSalaryPeriod(String from, String to) {
        try {
            format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            dateFrom = format.parse(from);
            dateTo = format.parse(to);
            if (dateFrom.after(dateTo)) {
                throw new WrongDataException("Date from must be before date to");
            }
        } catch (ParseException e) {
            System.out.println("Wrong date's format");
        }
        return ("Report for period " + from + " - " + to);
    }

    public void parsingData(String [] data, String [] names) {
        employeeData = new ArrayList<>();
        String [] currentData;
        EmployeeData currentEmployee;
        for (String datum : data) {
            currentEmployee = new EmployeeData();
            currentData = datum.split(" ");
            if (currentData.length != quantityOfSubstrings) {
                throw new WrongDataException("Wrong quantity data");
            }
            try {
                currentEmployee.currentDate = format.parse(currentData[0]);
            } catch (ParseException e) {
                System.out.println("Wrong date format");
            }
            if (currentEmployee.currentDate.before(dateFrom)
                    || currentEmployee.currentDate.after(dateTo)) {
                continue;
            }
            currentEmployee.name = currentData[1];
            if (checkNameValid(names, currentEmployee.name) == false) {
                continue;
            }
            try {
                currentEmployee.hours = Integer.parseInt(currentData[2]);
                currentEmployee.income = Integer.parseInt(currentData[3]);
            } catch (NumberFormatException e) {
                System.out.println("Wrong number format");
            }
            employeeData.add(currentEmployee);
        }
    }

    public boolean checkNameValid(String [] names, String name) {
        for (String currentName : names) {
            if (currentName.equals(name)) {
                return true;
            }
        }
        return false;
    }
}
