package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_WORK_DAY = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_WORKING_HOUR = 2;
    private static final int INDEX_INCOME = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate workDayDate;
        LocalDate dateFrom1 = getDateFromString(dateFrom);
        LocalDate dateTo1 = getDateFromString(dateTo);
        for (String name : names) {
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ");
            int salary = 0;
            for (String s : data) {
                String[] splitDataUnit = s.split(" ");
                if (name.equals(splitDataUnit[INDEX_NAME])) {
                    workDayDate = getDateFromString(splitDataUnit[INDEX_WORK_DAY]);
                    if (workDayDate.compareTo(dateFrom1) >= 0
                            && workDayDate.compareTo(dateTo1) <= 0) {
                        salary += (Integer.parseInt(splitDataUnit[INDEX_WORKING_HOUR])
                                * Integer.parseInt(splitDataUnit[INDEX_INCOME]));
                    }
                }
            }
            stringBuilder.append(salary);
        }
        return stringBuilder.toString();
    }

    private static LocalDate getDateFromString(String value) {
        return LocalDate.from(DATE_TIME_FORMATTER.parse(value));
    }
}
