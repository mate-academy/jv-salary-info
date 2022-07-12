package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR = 3;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        int[] employeesSalaries = new int[names.length];
        for (String info : data) {
            String[] splitInfo = info.split(" ");
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(splitInfo[NAME_INDEX])) {
                    if (LocalDate.parse(splitInfo[DATE_INDEX],
                            dateTimeFormatter).isAfter(localDateFrom)
                            && LocalDate.parse(splitInfo[DATE_INDEX],
                            dateTimeFormatter).isBefore(localDateTo)
                            || LocalDate.parse(splitInfo[DATE_INDEX],
                            dateTimeFormatter).isEqual(localDateFrom)
                            || LocalDate.parse(splitInfo[DATE_INDEX],
                            dateTimeFormatter).isEqual(localDateTo)) {
                        employeesSalaries[i] += Integer.parseInt(splitInfo[HOURS_INDEX])
                            * Integer.parseInt(splitInfo[SALARY_PER_HOUR]);
                    }
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(employeesSalaries[i]);
        }
        return stringBuilder.toString();
    }
}
