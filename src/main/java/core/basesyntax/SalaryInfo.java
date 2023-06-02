package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names.length == 0 || data.length == 0) {
            return "";
        }
        LocalDate dateReportFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate dateReportTo = LocalDate.parse(dateTo, formatter);
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        int sampleSalary;
        for (String sampleName : names) {
            sampleSalary = 0;
            for (String datas : data) {
                String[] record = datas.split(" ");
                if (record[NAME_INDEX].equals(sampleName)) {
                    LocalDate dateRecord = LocalDate.parse(record[DATE_INDEX], formatter);
                    if (dateRecord.isAfter(dateReportFrom) && dateRecord.isBefore(dateReportTo)
                            || dateRecord.equals(dateReportFrom) || dateRecord.equals(dateReportTo)) {
                        int recordSalary =
                                Integer.parseInt(record[HOURS_INDEX]) * Integer.parseInt(record[INCOME_PER_HOUR_INDEX]);
                        sampleSalary += recordSalary;
                    }
                }
            }
            builder.append(System.lineSeparator());
            builder.append(sampleName).append(" - ");
            builder.append(sampleSalary);
        }
        return builder.toString();
    }
}
