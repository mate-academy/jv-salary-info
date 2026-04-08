package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = System.lineSeparator();
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names == null || data == null || dateFrom == null || dateTo == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        LocalDate dateStartPeriod = LocalDate.parse(dateFrom, FORMAT);
        LocalDate dateEndPeriod = LocalDate.parse(dateTo, FORMAT);
        stringBuilder.append(dateStartPeriod.format(FORMAT))
                .append(" - ")
                .append(dateEndPeriod.format(FORMAT));
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            int salary = 0;
            for (int j = 0; j < data.length; j++) {
                String[] employeeData = data[j].split(" ");
                LocalDate date = LocalDate.parse(employeeData[DATE_INDEX], FORMAT);
                String nameInLoop = employeeData[NAME_INDEX];
                int totalWorkingHour = Integer.parseInt(employeeData[WORKING_HOUR_INDEX]);
                int salaryPerHour = Integer.parseInt(employeeData[SALARY_INDEX]);
                if (!date.isBefore(dateStartPeriod)
                        && !date.isAfter(dateEndPeriod)
                        && name.equals(nameInLoop)) {
                    salary += totalWorkingHour * salaryPerHour;
                }
            }
            stringBuilder.append(SEPARATOR).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
