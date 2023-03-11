package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder result = new StringBuilder("Report for period "
                + dateFrom + " - "
                + dateTo + System.lineSeparator());
        int income = 0;
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            for (String dataString : data) {
                String[] splitData = dataString.split(" ");
                int hoursInData = Integer.parseInt(splitData[2]);
                int incomePerHourInData = Integer.parseInt(splitData[3]);
                LocalDate date = LocalDate.parse(splitData[0], formatter);
                String nameInData = splitData[1];

                if (nameInData.equals(name) && fromDate.isBefore(date) && toDate.isAfter(date)) {
                    income += hoursInData * incomePerHourInData;
                }
            }
            result.append(name).append(" - ").append(income).append(System.lineSeparator());
            income = 0;
        }
        return  result.toString();
    }
}
