package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int DATA_LENGTH = 4;
    public static final int DATE_INDEX = 0;
    public static final int NAME_INDEX = 1;
    public static final int HOUR_INDEX = 2;
    public static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] salaries = new int[names.length];
        LocalDate to = LocalDate.parse(dateTo, FORMATTER);
        LocalDate from = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate tempDate;
        String[] tempData;

        for (String datum : data) {
            tempData = datum.split(" ", DATA_LENGTH);
            tempDate = LocalDate.parse(tempData[DATE_INDEX], FORMATTER);

            if ((tempDate.isAfter(from) || tempDate.isEqual(from))
                    && (tempDate.isBefore(to) || tempDate.isEqual(to))) {
                for (int i = 0; i < names.length; i++) {
                    if (tempData[NAME_INDEX].equals(names[i])) {
                        salaries[i] += Integer.parseInt(tempData[SALARY_INDEX])
                                * Integer.parseInt(tempData[HOUR_INDEX]);
                    }
                }
            }
        }

        StringBuilder resultString = new StringBuilder("Report for period " + dateFrom + " - "
                + dateTo + System.lineSeparator());
        for (int i = 0; i < names.length - 1; i++) {
            resultString.append(names[i]).append(" - ")
                    .append(salaries[i]).append(System.lineSeparator());
        }
        resultString.append(names[names.length - 1])
                .append(" - ").append(salaries[names.length - 1]);
        return resultString.toString();
    }
}
