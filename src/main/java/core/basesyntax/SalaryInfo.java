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
            String[] sorted = getDataByName(name, data);
            sorted = getDataByPeriod(sorted, dateFrom, dateTo);
            int salary = getSalaryForPeriod(sorted);
            result.append("\n").append(name).append(" - ").append(salary);
        }

        return result.toString();
    }

    private int getSalaryForPeriod(String[] dataLines) {
        int salaryForPeriod = 0;

        for (String data: dataLines) {
            int[] values = getHoursAndIncome(data);
            salaryForPeriod += values[values.length - 1] * values[values.length - 2];
        }
        return salaryForPeriod;
    }

    private String[] getDataByPeriod(String[] data, String dateFrom, String dateTo) {
        return Arrays.stream(data).filter(i -> convertToDate(split(i)[0])
                .isBefore(convertToDate(dateTo)) && convertToDate(split(i)[0])
                .isAfter(convertToDate(dateFrom))).toArray(String[]::new);
    }

    private String[] getDataByName(String name, String[] data) {
        return Arrays.stream(data).filter(i -> i.contains(name)).toArray(String[]::new);
    }

    private LocalDate convertToDate(String date) {
        return LocalDate.parse(date, FORMATTER);
    }

    private String[] split(String dataLine) {
        return dataLine.split(" ");
    }

    private int[] getHoursAndIncome(String dataLine) {
        String[] split = split(dataLine);
        return new int[]{Integer.parseInt(split[split.length - 1]),
                Integer.parseInt(split[split.length - 2])};
    }
}
