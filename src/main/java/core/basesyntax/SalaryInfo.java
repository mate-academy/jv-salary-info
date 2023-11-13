package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final Integer DATE_INDEX_IN_RECORD = 0;
    private static final Integer NAME_INDEX_IN_RECORD = 1;
    private static final Integer HOURS_INDEX_IN_RECORD = 2;
    private static final Integer PAYMENT_INDEX_IN_RECORD = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        LocalDate localDateFromRecord;
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ");
        builder.append(dateFrom).append(" - ");
        builder.append(dateTo);
        for (String name : names) {
            int salary = 0;
            builder.append(System.lineSeparator()).append(name).append(" - ");
            for (String record : data) {
                String[] recordArray = record.split(" ");
                localDateFromRecord = LocalDate.parse(recordArray[DATE_INDEX_IN_RECORD], FORMATTER);
                salary += compareRecordToName(name, recordArray, localDateFromRecord, localDateFrom, localDateTo);
            }
            builder.append(salary);
        }
        return builder.toString();
    }

    private int compareRecordToName(String name, String[] recordArray, LocalDate localDateFromRecord,
                                    LocalDate localDateFrom, LocalDate localDateTo) {
        if (name.equalsIgnoreCase(recordArray[NAME_INDEX_IN_RECORD])) {
            if ((localDateFromRecord.isAfter(localDateFrom)
                    || localDateFromRecord.isEqual(localDateFrom))
                    && (localDateFromRecord.isBefore(localDateTo)
                    || localDateFromRecord.isEqual(localDateTo))) {
                return Integer.parseInt(recordArray[HOURS_INDEX_IN_RECORD])
                        * Integer.parseInt(recordArray[PAYMENT_INDEX_IN_RECORD]);
            }
        }
        return 0;
    }
}
