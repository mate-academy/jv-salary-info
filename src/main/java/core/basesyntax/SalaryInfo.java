package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.uuuu");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOUR_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = LocalDate.parse(dateFrom,DATE_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo,DATE_FORMATTER);
        int salarySum = 0;
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name: names) {
            for (String dataLine : data) {
                String[] arrayDataLine = dataLine.split(" ");
                LocalDate today = LocalDate.parse(arrayDataLine[DATE_INDEX], DATE_FORMATTER);
                int hour = Integer.parseInt(arrayDataLine[HOUR_INDEX]);
                int salary = Integer.parseInt(arrayDataLine[SALARY_INDEX]);
                if (name.equals(arrayDataLine[NAME_INDEX])
                        && !from.isAfter(today)
                        && !to.isBefore(today)) {
                    salarySum += hour * salary;
                }
            }
            result.append(name)
                    .append(" - ")
                    .append(salarySum)
                    .append(System.lineSeparator());
            salarySum = 0;
        }
        return result.toString().trim();
    }
}