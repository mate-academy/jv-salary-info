package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final int INDEX_OF_DATE_IN_DATA = 0;
    public static final int INDEX_OF_EMPLOYEE_NAME = 1;
    public static final int INDEX_OF_EMPLOYEE_SALARY = 2;
    public static final int INDEX_OF_EMPLOYEE_SALARY_MULTIPLIER = 3;
    public static final DateTimeFormatter CURRENT_FORMAT
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder("Report for period ");
        report.append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, CURRENT_FORMAT);
        LocalDate localDateTo = LocalDate.parse(dateTo, CURRENT_FORMAT);
        for (String name : names) {
            int employeeSalary = 0;
            for (String dataString : data) {
                String[] parsedData = dataString.split("\\s");
                LocalDate dateOfData = LocalDate
                        .parse(parsedData[INDEX_OF_DATE_IN_DATA], CURRENT_FORMAT);
                if (isIncorrectDate(dateOfData, localDateFrom, localDateTo)
                        || !name.equals(parsedData[INDEX_OF_EMPLOYEE_NAME])) {
                    continue;
                }
                employeeSalary += Integer.parseInt(parsedData[INDEX_OF_EMPLOYEE_SALARY])
                        * Integer.parseInt(parsedData[INDEX_OF_EMPLOYEE_SALARY_MULTIPLIER]);
            }
            report.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(employeeSalary);
        }
        return report.toString();
    }

    private boolean isIncorrectDate(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return date.isBefore(dateFrom) || date.isAfter(dateTo);
    }
}
