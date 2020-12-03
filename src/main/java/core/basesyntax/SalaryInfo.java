package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SalaryInfo {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = getSalaryForPeriod(getDataForPeriod(name, data, dateFrom, dateTo));
            result.append("\n").append(name).append(" - ").append(salary);
        }

        return result.toString();
    }

    private int getSalaryForPeriod(String[][] data) {
        int salaryForPeriod = 0;
        for (String[] input : data) {
            salaryForPeriod += Integer.parseInt(input[2]) * Integer.parseInt(input[3]);
        }
        return salaryForPeriod;
    }

    private String[][] getDataForPeriod(String name, String[] data,
                                        String dateFrom, String dateTo) {
        return Arrays.stream(data).filter(i -> i.contains(name))
                .map(i -> i.split(" ")).filter(i -> convertToDate(i[0])
                                .isAfter(convertToDate(dateFrom)) && convertToDate(i[0])
                                .isBefore(convertToDate(dateTo))).toArray(String[][]::new);
    }

    public LocalDate convertToDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
