package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String SEPARATOR = " ";
    private static final long AND_ONE_MORE_DAY = 1L;
    private static final int EMPLOYEE_DATA_INDEX = 0;
    private static final int EMPLOYEE_NAME_INDEX = 1;
    private static final int WORKING_OURS = 2;
    private static final int INCOME_PER_OUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period ");
        stringBuilder.append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                var separatedData = record.split(SEPARATOR);
                var employeeData = LocalDate.parse(separatedData[EMPLOYEE_DATA_INDEX],
                        DATE_TIME_FORMATTER);
                var employeeName = separatedData[EMPLOYEE_NAME_INDEX];
                var workingOurs = Integer.parseInt(separatedData[WORKING_OURS]);
                var incomePerOur = Integer.parseInt(separatedData[INCOME_PER_OUR]);
                if (name.equals(employeeName)
                        && employeeData.isAfter(LocalDate.parse(dateFrom, DATE_TIME_FORMATTER)
                        .minusDays(AND_ONE_MORE_DAY))
                        && employeeData.isBefore(LocalDate.parse(dateTo, DATE_TIME_FORMATTER)
                        .plusDays(AND_ONE_MORE_DAY))) {
                    salary += workingOurs * incomePerOur;
                }
            }
            stringBuilder.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}
