package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORK_HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, FORMATTER);

        String[] employeeInfo;
        for (String name : names) {
            int income = 0;
            for (String value : data) {
                employeeInfo = value.split(" ");
                if (name.equals(employeeInfo[NAME_INDEX])) {
                    LocalDate workDate = LocalDate.parse(employeeInfo[DATE_INDEX], FORMATTER);

                    if ((workDate.isBefore(dateToFormatted)
                            || workDate.isEqual(dateToFormatted))
                            && (workDate.isAfter(dateFromFormatted)
                            || workDate.isEqual(dateFromFormatted))) {
                        income += Integer.parseInt(employeeInfo[WORK_HOUR_INDEX])
                                * Integer.parseInt(employeeInfo[INCOME_PER_HOUR_INDEX]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(income);
        }
        return stringBuilder.toString();
    }
}
