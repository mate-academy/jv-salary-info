package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_FROM_DATA = 0;
    private static final int NAME_FROM_DATA = 1;
    private static final int WORKING_HOURS_FROM_DATA = 2;
    private static final int PER_HOUR_FROM_DATA = 3;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromParsed = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToParsed = LocalDate.parse(dateTo, formatter);
        StringBuilder output = new StringBuilder();
        output.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int sumSalary = 0;
            for (String record : data) {
                String[] splitedRecord = record.split(" ");
                LocalDate recordsDate = LocalDate.parse(splitedRecord[DATE_FROM_DATA], formatter);
                String recordsName = splitedRecord[NAME_FROM_DATA];
                int recordsHours = Integer.parseInt(splitedRecord[WORKING_HOURS_FROM_DATA]);
                int recordsSalary = Integer.parseInt(splitedRecord[PER_HOUR_FROM_DATA]);
                if ((recordsDate.isAfter(dateFromParsed) || recordsDate.isEqual(dateFromParsed))
                        & (recordsDate.isBefore(dateToParsed) || recordsDate.isEqual(dateToParsed))
                        && name.equals(recordsName)) {
                    sumSalary += recordsSalary * recordsHours;
                }

            }
            output.append(System.lineSeparator()).append(name).append(" - ").append(sumSalary);
        }
        return output.toString();
    }
}
