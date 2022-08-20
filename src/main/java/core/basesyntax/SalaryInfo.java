package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS = 2;
    private static final int SALARY = 3;
    private static final String DATE_TIME_PATTERN = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = formatData(dateFrom);
        LocalDate toDate = formatData(dateTo);
        StringBuilder builder = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int income = 0;
            for (String dataInfo : data) {
                String[] dataParts = dataInfo.split(" ");
                if (formatData(dataParts[DATE]).isAfter(fromDate.minusDays(1))
                        && formatData(dataParts[DATE]).isBefore(toDate.plusDays(1))
                        && dataParts[NAME].equals(name)) {
                    income += calculateSalary(dataParts[HOURS], dataParts[SALARY]);
                }
            }
            builder.append(name).append(" - ").append(income).append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    private int calculateSalary(String hours, String salary) {
        return Integer.parseInt(hours) * Integer.parseInt(salary);
    }

    private LocalDate formatData(String data) {
        return LocalDate.parse(data, DateTimeFormatter.ofPattern(DATE_TIME_PATTERN));
    }
}
