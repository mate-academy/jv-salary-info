package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_OF_NAME_IN_DATA = 1;
    private static final int INDEX_OF_WORK_HOURS_IN_DATA = 2;
    private static final int INDEX_OF_SALARY_FOR_DAY_IN_DATA = 3;
    private static final int INDEX_OF_DATA = 0;
    private static final String REGEX = " ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int sum = 0;
            for (int j = 0; j < data.length; j++) {
                String[] splitData = data[j].split(REGEX);
                if (name.equals(data[j].split(REGEX)[INDEX_OF_NAME_IN_DATA])) {
                    LocalDate convertDate = LocalDate.parse(splitData[INDEX_OF_DATA], FORMATTER);
                    if (convertDate.isBefore(toDate) && convertDate.isAfter(fromDate)
                            || convertDate.isEqual(toDate) || convertDate.isEqual(fromDate)) {
                        sum += getSalaryPerDay(splitData[INDEX_OF_WORK_HOURS_IN_DATA],
                                splitData[INDEX_OF_SALARY_FOR_DAY_IN_DATA]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(sum);
        }
        return stringBuilder.toString();
    }

    private int getSalaryPerDay(String days, String salaryPerHour) {
        return (Integer.parseInt(days) * Integer.parseInt(salaryPerHour));
    }
}
