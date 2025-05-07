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
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);

        int[] salaries = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    String[] arrayDataOneDay = data[j].split(REGEX);
                    if (isDateInRange(LocalDate.parse(arrayDataOneDay[INDEX_DAY], formatter),
                            localDateFrom, localDateTo)) {
                        salaries[i] += Integer.parseInt(arrayDataOneDay[INDEX_HOURS_PER_DAY])
                                * Integer.parseInt(arrayDataOneDay[INDEX_SALARY_PER_HOUR]);
                    }
                }
            }
        }
        StringBuilder salaryInfo = new StringBuilder(PERIOD_FOR + dateFrom + SEPARATOR + dateTo);
        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(System.lineSeparator())
                    .append(names[i]).append(SEPARATOR).append(salaries[i]);
        }
        return salaryInfo.toString();
    }

    private boolean isDateInRange(LocalDate dateToCheck, LocalDate dateFrom, LocalDate dateTo) {
        return !dateToCheck.isAfter(dateTo) && !dateToCheck.isBefore(dateFrom);
    }
}
