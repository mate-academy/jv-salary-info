package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        int workingHour;
        int incomePerHour;
        int salary;

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            salary = 0;
            builder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ");
            for (String record : data) {
                String[] splitedRecord = record.split(" ");
                LocalDate dataDate = LocalDate.parse(splitedRecord[0], DATE_TIME_FORMATTER);
                if (name.equals(splitedRecord[1])
                        && !dataDate.isBefore(fromDate)
                        && !dataDate.isAfter(toDate)) {
                    workingHour = Integer.parseInt(splitedRecord[2]);
                    incomePerHour = Integer.parseInt(splitedRecord[3]);
                    salary += workingHour * incomePerHour;
                }
            }
            builder.append(salary);
        }
        String salaryInfo = builder.toString();

        return salaryInfo;
    }
}
