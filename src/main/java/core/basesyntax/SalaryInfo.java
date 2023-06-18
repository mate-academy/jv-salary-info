package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
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
                LocalDate recordsDate = LocalDate.parse(splitedRecord[0], formatter);
                String recordsName = splitedRecord[1];
                int recordsHours = Integer.parseInt(splitedRecord[2]);
                int recordsSalary = Integer.parseInt(splitedRecord[3]);
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
