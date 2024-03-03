package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String PERIOD_FOR = "Report for period ";
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String SEPARATOR = " - ";
    private static final String REGEX = " ";
    private static final int INDEX_DAY = 0;
    private static final int INDEX_HOURS_PER_DAY = 2;
    private static final int INDEX_SALARY_PER_HOUR = 3;
    private LocalDate localDateFrom;
    private LocalDate localDate;
    private LocalDate localDateTo;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    private StringBuilder salaryInfo;

    private int[] salary;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    String[] s = data[j].split(REGEX);
                    if (dateValidator(dateFrom, dateTo, s[INDEX_DAY])) {
                        salary[i] += Integer.parseInt(s[INDEX_HOURS_PER_DAY])
                                * Integer.parseInt(s[INDEX_SALARY_PER_HOUR]);
                    }
                }
            }
        }
        salaryInfo = new StringBuilder(PERIOD_FOR + dateFrom + SEPARATOR + dateTo);
        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(System.lineSeparator())
                    .append(names[i]).append(SEPARATOR).append(salary[i]);
        }
        return salaryInfo.toString();
    }

    private boolean dateValidator(String dateFrom, String dateTo, String date) {
        localDate = LocalDate.parse(date, dateTimeFormatter);
        localDateFrom = LocalDate.parse(dateFrom, dateTimeFormatter);
        localDateTo = LocalDate.parse(dateTo, dateTimeFormatter);
        return localDate.isAfter(localDateFrom)
                && localDate.isBefore(localDateTo)
                || dateFrom.equals(date) || dateTo.equals(date);
    }
}
