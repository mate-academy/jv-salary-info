package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int FIRST_SALARY_SPLIT_INDEX = 2;
    private static final int SECOND_SALARY_SPLIT_INDEX = 3;
    private static final int NAME_SPLIT_INDEX = 1;
    private static final int DATE_SPLIT_INDEX = 0;
    private static final String WHITESPACE = " ";
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        int[] salary = new int[names.length];
        LocalDate firstDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate lastDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        for (int i = 0; i < names.length; i++) {
            for (String datum : data) {
                LocalDate currentDate = splitDate(datum);

                if (currentDate.isAfter(lastDate)) {
                    continue;
                }

                if ((currentDate.isAfter(firstDate) || currentDate.equals(firstDate))
                        && (currentDate.isBefore(lastDate) || currentDate.equals(lastDate))
                        && names[i].equals(splitName(datum))) {
                    salary[i] += salaryCounter(datum);
                }
            }
            builder.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary[i]);
        }
        return builder.toString();
    }

    private int salaryCounter(String data) {
        String[] dataSplit = data.split(WHITESPACE);
        return Integer.valueOf(dataSplit[FIRST_SALARY_SPLIT_INDEX])
                * Integer.valueOf(dataSplit[SECOND_SALARY_SPLIT_INDEX]);
    }

    private String splitName(String data) {
        String[] dataSplit = data.split(WHITESPACE);
        return dataSplit[NAME_SPLIT_INDEX];
    }

    private LocalDate splitDate(String data) {
        String[] dataSplit = data.split(WHITESPACE);
        return LocalDate.parse(dataSplit[DATE_SPLIT_INDEX], DATE_TIME_FORMATTER);
    }
}
