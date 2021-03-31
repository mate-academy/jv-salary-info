package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salary = 0;
        StringBuilder salaries = new StringBuilder("Report for period "
                + dateFrom
                + " - " + dateTo);

        for (String obj: names) {
            salary = 0;
            for (String dataObj: data) {
                if (obj.equals(dataObj.split(" ")[1])
                        && dateIsInRange(dateFrom, dateTo, dataObj.split(" ")[0])) {
                    salary += getSalary(dataObj);
                }
            }
            salaries.append("\n" + obj + " - " + salary);
        }
        return salaries.toString();
    }

    private int getSalary(String dataObj) {
        return Integer.parseInt(dataObj.split(" ")[2])
                * Integer.parseInt(dataObj.split(" ")[3]);
    }

    private LocalDate getDate(String input) {
        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return LocalDate.parse(input, formatter);
        } catch (DateTimeParseException exc) {
            throw exc;
        }
    }

    private boolean dateIsInRange(String dateFrom, String dateTo, String dateToCheck) {
        LocalDate dateFr = getDate(dateFrom);
        LocalDate dateT = getDate(dateTo);
        LocalDate checkDate = getDate(dateToCheck);
        return !checkDate.isAfter(dateT) && !checkDate.isBefore(dateFr);
    }
}
