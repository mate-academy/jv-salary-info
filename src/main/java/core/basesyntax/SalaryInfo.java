package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = System.lineSeparator();
    private static final int DATE_INDEX_IN_LOOP = 0;
    private static final int NAME_INDEX_IN_LOOP = 1;
    private static final int TOTAL_INDEX_WORKING_HOUR = 2;
    private static final int SALARY_INDEX_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder("Report for period ");

        LocalDate dateOfStartPeriod = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateOfEndPeriod = LocalDate.parse(dateTo, FORMATTER);
        stringBuilder.append(dateOfStartPeriod.format(FORMATTER))
                .append(" - ")
                .append(dateOfEndPeriod.format(FORMATTER));

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            long salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] employeeData = data[j].split(" ");
                LocalDate date = LocalDate.parse(employeeData[DATE_INDEX_IN_LOOP], FORMATTER);
                String nameInLoop = employeeData[NAME_INDEX_IN_LOOP];
                int totalWorkingHour = Integer.parseInt(employeeData[TOTAL_INDEX_WORKING_HOUR]);
                int salaryPerHour = Integer.parseInt(employeeData[SALARY_INDEX_PER_HOUR]);

                if (!date.isBefore(dateOfStartPeriod)
                        && !date.isAfter(dateOfEndPeriod)
                        && name.equals(nameInLoop)) {
                    salary += totalWorkingHour * salaryPerHour;
                }
            }
            stringBuilder.append(SEPARATOR).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
