package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final int dateLength = dateFrom.length();
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMAT_DATE);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMAT_DATE);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            builder.append(System.lineSeparator()).append(name).append(" - ");
            int salaryPerPeriod = 0;
            for (String record: data) {
                if (record.contains(name)) {
                    final int nameLength = name.length();
                    String dateInRecord = record.substring(0,dateLength);
                    LocalDate localDateForRecord = LocalDate.parse(dateInRecord, FORMAT_DATE);
                    if ((localDateForRecord.isAfter(localDateFrom)
                            || localDateForRecord.isEqual(localDateFrom))
                            && (localDateForRecord.isBefore(localDateTo)
                            || localDateForRecord.isEqual(localDateTo))) {
                        String recordForEmployeeByDate = record.substring(dateLength
                                + nameLength + 2);
                        final int indexOfGap = recordForEmployeeByDate.indexOf(" ");
                        salaryPerPeriod += Integer.parseInt(recordForEmployeeByDate
                                .substring(0,indexOfGap))
                                * Integer.parseInt(recordForEmployeeByDate
                                .substring(indexOfGap + 1));
                    }
                }
            }
            builder.append(salaryPerPeriod);
        }
        return builder.toString();
    }
}
