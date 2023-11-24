package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String FORMATTER_PATTERN = "dd.MM.yyyy";
    private static final String HYPHEN_WITH_SPACES = " - ";
    private static final String ENTRY_PHRASE = "Report for period ";
    private static final int DATE_ROW_INDEX = 0;
    private static final int NAME_ROW_INDEX = 1;
    private static final int NUMBER_OF_DAYS_ROW_INDEX = 2;
    private static final int SALARY_ROW_INDEX = 3;
    private int employeeSalary;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append(ENTRY_PHRASE)
                .append(dateFrom)
                .append(HYPHEN_WITH_SPACES)
                .append(dateTo);
        for (String name: names) {
            salaryInfo.append(System.lineSeparator())
                    .append(name)
                    .append(HYPHEN_WITH_SPACES)
                    .append(getEmployeeSalary(data, name, dateFrom, dateTo));
        }
        return salaryInfo.toString();
    }

    private int getEmployeeSalary(String[] data, String name, String dateFrom, String dateTo) {
        employeeSalary = 0;
        for (int i = 0; i < data.length; i++) {
            String[] row = data[i].split(" ");
            if (row[NAME_ROW_INDEX].equals(name)
                    && isDateInRange(dateFrom, dateTo, row[DATE_ROW_INDEX])) {
                employeeSalary += Integer.valueOf(row[NUMBER_OF_DAYS_ROW_INDEX])
                        * Integer.valueOf(row[SALARY_ROW_INDEX]);
            }
        }
        return employeeSalary;
    }

    private boolean isDateInRange(String dateFrom, String dateTo, String actualDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATTER_PATTERN);
        return LocalDate.parse(dateFrom, formatter)
                .isBefore(LocalDate.parse(actualDate, formatter))
                && LocalDate.parse(dateTo, formatter)
                .isAfter(LocalDate.parse(actualDate, formatter))
                || dateFrom.equals(actualDate)
                || dateTo.equals(actualDate);
    }
}
