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

        for (String name: names) {
            salary = 0;
            for (String recordFromDB: data) {
                if (name.equals(recordFromDB.split(" ")[1])
                        && dateIsInRange(dateFrom, dateTo, recordFromDB.split(" ")[0])) {
                    salary += getSalary(recordFromDB);
                }
            }
            salaries.append("\n" + name + " - " + salary);
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
        LocalDate localDateFrom = getDate(dateFrom);
        LocalDate localDateTo = getDate(dateTo);
        LocalDate localDateToCheck = getDate(dateToCheck);
        return !localDateToCheck.isAfter(localDateTo) && !localDateToCheck.isBefore(localDateFrom);
    }
}
