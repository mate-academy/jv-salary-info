package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builderPeriod = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        StringBuilder builderSalaries = new StringBuilder();
        for (String name : names) {
            int salary = 0;
            for (String dataRecord : data) {
                String[] dataRecordArray = dataRecord.split(" ");
                if ((dataRecordArray[1].equals(name))
                        && (LocalDate.parse(dataRecordArray[0], FORMATTER).isAfter(fromDate)
                        || LocalDate.parse(dataRecordArray[0], FORMATTER).isEqual(fromDate))
                        && ((LocalDate.parse(dataRecordArray[0], FORMATTER).isBefore(toDate)
                        || LocalDate.parse(dataRecordArray[0], FORMATTER).isEqual(toDate)))) {
                    salary = salary + Integer.parseInt(dataRecordArray[2])
                                * Integer.parseInt(dataRecordArray[3]);
                }
            }
            builderSalaries.append(System.lineSeparator())
                        .append(name).append(" - ")
                        .append(salary);
        }
        return builderPeriod.append(builderSalaries).toString();
    }
}
