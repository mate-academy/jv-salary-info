package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final String FORMAT_STRING = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_STRING);
        String [] parsedData;
        LocalDate dateFromD;
        LocalDate dateToD;
        LocalDate day;
        int employeeSalary;

        try {
            dateFromD = LocalDate.parse(dateFrom, formatter);
            dateToD = LocalDate.parse(dateTo, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Could not parse date ", e);
        }
        StringBuilder salaryInfo = new StringBuilder("Report for period " + dateFrom
                + " - " + dateTo);

        for (String name : names) {
            employeeSalary = 0;
            for (String str : data) {
                parsedData = str.split(" ");
                day = LocalDate.parse(parsedData[0], formatter);
                try {
                    if (name.equals(parsedData[1]) && ((dateFromD.isBefore(day)
                            || dateFromD.equals(day)) && (dateToD.isAfter(day)
                            || dateToD.equals(day)))) {
                        employeeSalary = employeeSalary + Integer.parseInt(parsedData[2])
                                * Integer.parseInt(parsedData[3]);
                    }
                } catch (DateTimeParseException e) {
                    throw new RuntimeException("Not correct day!", e);
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ")
                    .append(employeeSalary);
        }
        return salaryInfo.toString();
    }
}
