package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class SalaryInfo {

    ArrayList<String> Employee = new ArrayList<String>();
    public int EmployeeCheck(String name, int hours, int salaryPerHour) {
        if (Employee.contains(name)) {
            return -1;
        } else {
            Employee.add(name);
            return hours * salaryPerHour;
        }
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final LocalDate DATE_FROM;
        final LocalDate DATE_TO;
        LocalDate DAY_USER_WORKS;
        String currentUser;
        int currentUserSumSalary = 0;
        StringBuilder output = new StringBuilder();

        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd.MM.yyyy");
            DATE_FROM = LocalDate.parse(dateFrom, formatter);
        } catch (DateTimeParseException e) {
           throw e;
        }

        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd.MM.yyyy");
            DATE_TO = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }

        output.append("Report for period ");
        output.append(DATE_FROM)
                .append("- ")
                .append(DATE_TO)
                .append(System.lineSeparator());

        for (int i = 0; i < data.length; i++) {
            String[] arrayOfEmployees = data[i].split(" ");
            if ((EmployeeCheck(arrayOfEmployees[1], Integer.parseInt(arrayOfEmployees[2]),
                    Integer.parseInt(arrayOfEmployees[3]))) >= 0) {

                for (int k = 0; k < data.length; k++) {
                  
            try {
                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern("dd.MM.yyyy");
               DAY_USER_WORKS = LocalDate.parse(arrayOfEmployees[0], formatter);
            } catch (DateTimeParseException e) {
                throw e;
            }

            if (DATE_FROM.isBefore(DAY_USER_WORKS) && DATE_TO.isAfter(DAY_USER_WORKS)) {
               currentUserSumSalary += EmployeeCheck(arrayOfEmployees[1], Integer.parseInt(arrayOfEmployees[2]),
                       Integer.parseInt(arrayOfEmployees[3]));
            }


        }

        return output.toString();
    }
}
