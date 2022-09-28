package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int DATE_INDEX = 0;
    public static final int HOURS_WORKED_INDEX = 2;
    public static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromFormat = parseDate(dateFrom);
        LocalDate dateToFormat = parseDate(dateTo);
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            result.append(name).append(" - ");
            int sum = 0;
            for (String info : data) {
                if (!info.contains(name)) {
                    continue;
                } else {
                    String[] infoSplit = info.split(" ");
                    int hoursWorked = Integer.parseInt(infoSplit[HOURS_WORKED_INDEX]);
                    int incomePerHour = Integer.parseInt(infoSplit[INCOME_PER_HOUR_INDEX]);
                    LocalDate workedDate = parseDate(infoSplit[DATE_INDEX]);
                    if (workedDate.isAfter(dateFromFormat) && workedDate.isBefore(dateToFormat)
                            || workedDate.isEqual(dateFromFormat)
                            || workedDate.isEqual(dateToFormat)) {
                        sum += hoursWorked * incomePerHour;
                    }
                }
            }
            result.append(sum).append(System.lineSeparator());
        }
        return result.toString().trim();
    }

    public static LocalDate parseDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
