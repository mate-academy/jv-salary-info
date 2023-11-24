package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final byte DATE_INDEX = 0;
    private static final byte NAME_INDEX = 1;
    private static final byte WORKING_HOUR_INDEX = 2;
    private static final byte INCOME_PER_HOUR_INDEX = 3;

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
                LocalDate dataDate
                        = LocalDate.parse(splitedDataString[DATE_INDEX], DATE_TIME_FORMATTER);
                if (
                        !dataDate.isBefore(fromDate)
                        && !dataDate.isAfter(toDate)
                        && name.equals(splitedDataString[NAME_INDEX])
                ) {
                    salary += (Integer.parseInt(splitedDataString[WORKING_HOUR_INDEX])
                                    * Integer.parseInt(splitedDataString[INCOME_PER_HOUR_INDEX]));
                }
            }
            salaryInfo.append(salary);
        }
        return salaryInfo.toString();
    }
}
