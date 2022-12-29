package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOUR_INDEX = 2;
    public static final int PAYMENT_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = parseToLocalDate(dateFrom);
        LocalDate localDateTo = parseToLocalDate(dateTo);
        StringBuilder result = new StringBuilder("Report for period ");
        result.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] splitData = record.split(" ");
                LocalDate localDateFromData = parseToLocalDate(splitData[DATE_INDEX]);
                if (splitData[NAME_INDEX].equals(name)
                        & (localDateFromData.isAfter(localDateFrom)
                        || localDateFromData.isEqual(localDateFrom))
                        & (localDateFromData.isBefore(localDateTo)
                        || localDateFromData.isEqual(localDateTo))) {
                    salary += Integer.parseInt(splitData[HOUR_INDEX])
                            * Integer.parseInt(splitData[PAYMENT_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }

    public static LocalDate parseToLocalDate(String dateIn) {
        return LocalDate.parse(dateIn, FORMATTER);
    }
}
