package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period "
                + dateFrom + " - "
                + dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        int income = 0;
        for (String name : names) {
            for (String dataString : data) {
                String[] splitData = dataString.split(" ");
                int hoursInData = Integer.parseInt(splitData[2]);
                int incomePerHourInData = Integer.parseInt(splitData[3]);
                LocalDate date = LocalDate.parse(splitData[0], FORMATTER);
                String nameInData = splitData[1];
                if (nameInData.equals(name) && fromDate.isBefore(date)
                        && (toDate.isEqual(date) || toDate.isAfter(date))) {
                    income = income + (hoursInData * incomePerHourInData);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(income);
            income = 0;
        }
        return result.toString();
    }
}
