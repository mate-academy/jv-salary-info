package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SPLIT = " ";
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate firstDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate secondDate = LocalDate.parse(dateTo, FORMATTER);
        int namesLength = names.length;
        int[] salariesSum = getSalariesSum(namesLength, data, names, firstDate, secondDate);
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < namesLength; i++) {
            report.append(System.lineSeparator()).append(names[i]).append(" - ")
                    .append(salariesSum[i]);
        }
        return report.toString();
    }

    private int[] getSalariesSum(int namesLength, String[] data, String[] names,
                                 LocalDate firstDate, LocalDate secondDate) {
        int[] salaries = new int[namesLength];
        for (String info : data) {
            String[] arrayLine = info.split(SPLIT);
            for (int j = 0; j < namesLength; j++) {
                LocalDate date = LocalDate.parse(arrayLine[DATE_INDEX], FORMATTER);
                if (names[j].equals(arrayLine[NAME_INDEX])
                        && (date.isAfter(firstDate) && date.isBefore(secondDate)
                        || date.isEqual(secondDate))) {
                    salaries[j] += Integer.parseInt(arrayLine[WORKING_HOUR_INDEX])
                            * Integer.parseInt(arrayLine[INCOME_PER_HOUR_INDEX]);
                }
            }
        }
        return salaries;
    }
}
