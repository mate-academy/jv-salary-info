package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder outputString = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String dataEmployeeName : names) {
            int totalEmployeeSalary = 0;
            for (String employeeRecord : data) {
                String[] splittedEmployeeRecord = employeeRecord.split(" ");

                if (!dataEmployeeName.equals(splittedEmployeeRecord[1])) {
                    continue;
                }

                LocalDate ldDateFrom = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate ldDateTo = LocalDate.parse(dateTo, FORMATTER);
                LocalDate ldRecordDate = LocalDate.parse(splittedEmployeeRecord[0], FORMATTER);
                if ((ldRecordDate.equals(ldDateFrom) || ldRecordDate.isAfter(ldDateFrom))
                        && (ldRecordDate.equals(ldDateTo) || ldRecordDate.isBefore(ldDateTo))) {
                    totalEmployeeSalary += Integer.parseInt(splittedEmployeeRecord[2])
                            * Integer.parseInt(splittedEmployeeRecord[3]);
                }
            }
            outputString.append(System.lineSeparator())
                        .append(dataEmployeeName)
                        .append(" - ")
                        .append(totalEmployeeSalary);

        }
        return outputString.toString();
    }
}
