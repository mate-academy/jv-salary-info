package core.basesyntax;

import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_FROM_DATA = 0;
    private static final int NAME_FROM_DATA = 1;
    private static final int HOURS_FROM_DATA = 2;
    private static final int SALARY_PER_HOUR_FROM_DATA = 3;
    private static final String DASH = " - ";
    private static final String SPACE = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] totalSalary = new int[names.length];
        int counterForTotalSalary = 0;
        String[] splitedDataForm;
        LocalDate date;
        LocalDate dateF = stringToDate(dateFrom);
        LocalDate dateT = stringToDate(dateTo);
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            for (String currentData: data) {
                splitedDataForm = currentData.split(SPACE);
                date = stringToDate(splitedDataForm[DATE_FROM_DATA]);
                if (!splitedDataForm[NAME_FROM_DATA].equals(name)) {
                    continue;
                }
                if ((date.isAfter(dateF) || date.isEqual(dateF))
                        && (date.isBefore(dateT) || date.isEqual(dateT))) {
                    totalSalary[counterForTotalSalary]
                            += Integer.parseInt(splitedDataForm[HOURS_FROM_DATA])
                            * Integer.parseInt(splitedDataForm[SALARY_PER_HOUR_FROM_DATA]);
                }
            }
            result.append(System.lineSeparator()).append(name)
                    .append(DASH).append(totalSalary[counterForTotalSalary]);
            counterForTotalSalary++;
        }
        return result.toString();
    }

    public static LocalDate stringToDate(String dateToConvert) {
        return parse(dateToConvert, FORMATTER);
    }
}
