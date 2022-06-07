package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_OF_WORK_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;
    private static final String SEPARATOR = System.lineSeparator();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] arrayOfData;

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                arrayOfData = line.split(" ");
                LocalDate currentDate = LocalDate.parse(arrayOfData[DATE_INDEX],
                        DATE_TIME_FORMATTER);
                int hours = Integer.parseInt(arrayOfData[HOURS_OF_WORK_INDEX]);
                int income = Integer.parseInt(arrayOfData[INCOME_PER_HOUR_INDEX]);
                if (arrayOfData[NAME_INDEX].equals(name)
                        && fromDate.compareTo(currentDate) <= 0
                        && toDate.compareTo(currentDate) >= 0) {
                    salary += hours * income;
                }
            }
            result.append(SEPARATOR).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
