package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class SalaryInfo {
    private static final DateTimeFormatter
            DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();

        LocalDate from = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate to = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        int[] salaryPerEmployee = new int[names.length];
        for (String date : data) {
            String[] parts = date.split(" ");
            LocalDate employeeDate = LocalDate.parse(parts[0], DATE_TIME_FORMATTER);
            if (!employeeDate.isBefore(from) && !employeeDate.isAfter(to)) {
                for (int j = 0; j < names.length; j++) {
                    if (parts[NAME_INDEX].equals(names[j])) {
                        salaryPerEmployee[j] += Integer.parseInt(parts[HOURS_INDEX])
                                * Integer.parseInt(parts[SALARY_PER_HOUR_INDEX]);
                    }
                }
            }
        }

        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        for (int i = 0; i < salaryPerEmployee.length; i++) {
            joiner.add(names[i] + " - " + salaryPerEmployee[i]);
        }
        result.append(joiner.toString());

        return result.toString();
    }
}
