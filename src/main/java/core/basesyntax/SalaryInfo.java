package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final int dateIndex = 0;
    private final int nameIndex = 1;
    private final int salaryIndex = 2;
    private final int timeIndex = 3;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateFormatter);

        StringBuilder report = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int total = 0;
            report.append(System.lineSeparator());

            for (String record : data) {
                String[] recordValue = record.split(" ");

                String dateFromRecord = recordValue[dateIndex];
                String nameFromRecord = recordValue[nameIndex];
                int salaryFromRecord = Integer.valueOf(recordValue[salaryIndex]);
                int timeFromRecord = Integer.valueOf(recordValue[timeIndex]);

                if (name.equals(nameFromRecord)) {
                    LocalDate localDate = LocalDate.parse(dateFromRecord, dateFormatter);

                    if (!localDate.isBefore(localDateFrom) && !localDate.isAfter(localDateTo)) {
                        total += salaryFromRecord * timeFromRecord;
                    }
                }
            }
            report.append(name).append(" - ").append(total);
        }
        return report.toString();
    }
}
