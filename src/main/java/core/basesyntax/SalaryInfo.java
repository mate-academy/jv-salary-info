package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int WORKING_HOURS_INDEX = 2;
    public static final int HOURLY_PRICE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);

        for (String name : names) {
            int salary = 0;
            for (String newData : data) {
                String[] split = newData.split(" ");
                LocalDate date = LocalDate.parse(split[DATE_INDEX], formatter);
                String workerName = split[NAME_INDEX];
                int hours = Integer.parseInt(split[WORKING_HOURS_INDEX]);
                int hourlySalary = Integer.parseInt(split[HOURLY_PRICE_INDEX]);
                if (name.equals(workerName)
                        && !date.isBefore(startDate)
                        && !date.isAfter(endDate)) {
                    salary += hours * hourlySalary;
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
