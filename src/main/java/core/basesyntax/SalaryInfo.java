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

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] totalSalary = new int[names.length];
        String[] splitedDataForm;
        LocalDate date;
        LocalDate dateF = stringToDate(dateFrom);
        LocalDate dateT = stringToDate(dateTo);
        for (String currentData: data) {
            splitedDataForm = currentData.split(" ");
            date = stringToDate(splitedDataForm[DATE_FROM_DATA]);
            if ((date.isAfter(dateF) || date.isEqual(dateF))
                    && (date.isBefore(dateT) || date.isEqual(dateT))) {
                for (int i = 0; i < names.length; i++) {
                    if (splitedDataForm[NAME_FROM_DATA].equals(names[i])) {
                        totalSalary[i] += Integer.parseInt(splitedDataForm[HOURS_FROM_DATA])
                                * Integer.parseInt(splitedDataForm[SALARY_PER_HOUR_FROM_DATA]);
                    }
                }
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(totalSalary[i]);
        }
        return result.toString();
    }

    public static LocalDate stringToDate(CharSequence dateToConvert) {
        return parse(dateToConvert, FORMATTER);
    }
}
