package core.basesyntax;

import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int HOURS_PER_DAY = 2;
    private static final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        LocalDate startDate = parseStringToDate(dateFrom);
        LocalDate endDate = parseStringToDate(dateTo);
        for (String employeeName : names) {
            reportBuilder.append(employeeName).append(" - ");
            int salaryOfEmployee = 0;
            for (String employeeInfo : data) {
                String[] employeeInfoArray = employeeInfo.split(" ");
                LocalDate dateOfDataArray = parseStringToDate(employeeInfoArray[DATE]);
                if (employeeName.equals(employeeInfoArray[NAME])
                        && dateOfDataArray.isAfter(startDate)
                        && dateOfDataArray.isBefore(endDate.plusDays(1))) {
                    salaryOfEmployee += Integer.parseInt(employeeInfoArray[HOURS_PER_DAY])
                            * Integer.parseInt(employeeInfoArray[INCOME_PER_HOUR]);
                }
            }
            reportBuilder.append(salaryOfEmployee).append(System.lineSeparator());
        }
        return reportBuilder.toString().trim();
    }

    private LocalDate parseStringToDate(String date) {
        return parse(date, FORMATTER);
    }
}
