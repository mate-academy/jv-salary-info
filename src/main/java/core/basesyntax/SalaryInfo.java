package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMATTER);
        int salary = 0;
        for (String name : names) {
            for (String dataString : data) {
                String[] splitData = dataString.split(" ");
                LocalDate date = LocalDate.parse(splitData[0], DATE_FORMATTER);
                String nameInData = splitData[1];
                if (nameInData.equals(name) && fromDate.isBefore(date)
                        && (toDate.isEqual(date) || toDate.isAfter(date))) {
                    int hoursInData = Integer.parseInt(splitData[2]);
                    int incomePerHourInData = Integer.parseInt(splitData[3]);
                    salary += hoursInData * incomePerHourInData;

                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
            salary = 0;
        }
        return result.toString();
    }
}
