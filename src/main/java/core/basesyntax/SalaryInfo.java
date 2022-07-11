package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final String formatString = "dd.MM.yyyy";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatString);
        String [] parsedData;
        LocalDate dateFromD;
        LocalDate dateToD;
        LocalDate day;
        int employeeSalary;

        try {
            dateFromD = LocalDate.parse(dateFrom, formatter);
            dateToD = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException ex) {
            System.out.println("Not correct date!");
            return null;
        }
        StringBuilder salaryInfo = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);

        for (String name : names) {
            employeeSalary = 0;
            for (String str : data) {
                parsedData = str.split(" ");
                if (name.equals(parsedData[1])) {
                    try {
                        day = LocalDate.parse(parsedData[0], formatter);
                        if ((dateFromD.isBefore(day) || dateFromD.equals(day))
                                && (dateToD.isAfter(day) || dateToD.equals(day))) {
                            employeeSalary = employeeSalary + Integer.parseInt(parsedData[2])
                                    * Integer.parseInt(parsedData[3]);
                        }
                    } catch (DateTimeParseException ex) {
                        System.out.println("Not correct day!");
                    }
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ")
                    .append(employeeSalary);
        }
        return salaryInfo.toString();
    }
}
