package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final Integer dateIndexInRecord = 0;
    private static final Integer nameIndexInRecord = 1;
    private static final Integer hoursIndexInRecord = 2;
    private static final Integer paymentIndexInRecord = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = stringToDate(dateFrom);
        LocalDate localDateTo = stringToDate(dateTo);
        LocalDate localDateFromRecord;
        StringBuilder builder = new StringBuilder();
        int salary;
        builder.append("Report for period ");
        builder.append(dateFrom).append(" - ");
        builder.append(dateTo);
        for (String name : names) {
            salary = 0;
            builder.append(System.lineSeparator()).append(name).append(" - ");
            for (String record : data) {
                String[] recordArray = record.split(" ");
                localDateFromRecord = stringToDate(recordArray[dateIndexInRecord]);
                if (name.equalsIgnoreCase(recordArray[nameIndexInRecord])) {
                    if ((localDateFromRecord.isAfter(localDateFrom)
                            || localDateFromRecord.isEqual(localDateFrom))
                            && (localDateFromRecord.isBefore(localDateTo)
                            || localDateFromRecord.isEqual(localDateTo))) {
                        salary += Integer.parseInt(recordArray[hoursIndexInRecord])
                                * Integer.parseInt(recordArray[paymentIndexInRecord]);
                    }
                }
            }
            builder.append(salary);
        }
        return builder.toString();
    }

    private LocalDate stringToDate(String dateString) {
        return LocalDate.parse(dateString, FORMATTER);
    }
}
