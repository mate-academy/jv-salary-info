package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final int SALARY_DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                                                                .ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        long[] salariesForPeriod = new long[names.length];
        LocalDate fromLocalDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate toLocalDate = LocalDate.parse(dateTo, dateTimeFormatter);

        for (String employeeSalary : data) {
            String[] params = employeeSalary.split(" ");
            LocalDate currentDate = LocalDate.parse(params[SALARY_DATE_INDEX], dateTimeFormatter);
            if (!isDateValid(currentDate, fromLocalDate, toLocalDate)) {
                continue;
            }

            int employeeIndex = getEmployeeIndex(names, params[NAME_INDEX]);
            salariesForPeriod[employeeIndex] += Long.parseLong(params[WORKING_HOUR_INDEX])
                                                * Long.parseLong(params[SALARY_PER_HOUR_INDEX]);
        }

        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator()).append(names[i])
                    .append(" - ").append(salariesForPeriod[i]);
        }
        return report.toString();
    }

    private int getEmployeeIndex(String[] names, String currentName) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(currentName)) {
                return i;
            }
        }
        throw new EmployeeNameNotFoundException("Can't find employee index");
    }

    private boolean isDateValid(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return date.compareTo(dateFrom) >= 0 && date.compareTo(dateTo) <= 0;
    }
}
