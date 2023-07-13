package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        salaryInfo
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            salaryInfo
                    .append(System.lineSeparator())
                    .append(name)
                    .append(" - ");
            int salary = 0;
            for (String dataString : data) {
                String[] splitedDataString = dataString.split(" ");
                LocalDate dataDate = LocalDate.parse(splitedDataString[0], DATE_TIME_FORMATTER);
                if (
                        !dataDate.isBefore(fromDate)
                        && !dataDate.isAfter(toDate)
                        && name.equals(splitedDataString[1])
                ) {
                    salary += (Integer.parseInt(splitedDataString[2])
                                    * Integer.parseInt(splitedDataString[3]));
                }
            }
            salaryInfo.append(salary);
        }
        return salaryInfo.toString();
    }
}
