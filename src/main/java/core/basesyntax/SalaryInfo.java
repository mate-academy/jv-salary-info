package core.basesyntax;

import static java.time.LocalDate.parse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

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
                LocalDate dateOfDataArray = parseStringToDate(employeeInfoArray[0]);
                if (employeeName.equals(employeeInfoArray[1])
                        && dateOfDataArray.isAfter(startDate)
                        && dateOfDataArray.isBefore(endDate.plusDays(1))) {
                    salaryOfEmployee += Integer.parseInt(employeeInfoArray[2])
                            * Integer.parseInt(employeeInfoArray[3]);
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
