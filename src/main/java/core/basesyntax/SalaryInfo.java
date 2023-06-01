package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (names.length == 0 || data.length == 0) {
            return "";
        }
        try {
            LocalDate date1 = LocalDate.parse(dateFrom, formatter);
            LocalDate date2 = LocalDate.parse(dateTo, formatter);
            StringBuilder builder = new StringBuilder("Report for period ");
            builder.append(dateFrom).append(" - ").append(dateTo);
            int sampleSalary;
            for (String sampleName : names) {
                sampleSalary = 0;
                for (String datas : data) {
                    String[] record = datas.split(" ");
                    if (record[1].equals(sampleName)) {
                        LocalDate dateRecord = LocalDate.parse(record[0], formatter);
                        if (dateRecord.isAfter(date1) && dateRecord.isBefore(date2)
                                || dateRecord.equals(date1) || dateRecord.equals(date2)) {
                            int recordSalary =
                                    Integer.parseInt(record[2]) * Integer.parseInt(record[3]);
                            sampleSalary += recordSalary;
                        }
                    }
                }
                builder.append(System.lineSeparator());
                builder.append(sampleName).append(" - ");
                builder.append(sampleSalary);
            }
            return builder.toString();
        } catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", dateFrom);
            throw exc;
        }
    }
}
