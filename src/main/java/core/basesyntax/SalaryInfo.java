package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private StringBuilder builder;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = stringToDate(dateFrom);
        LocalDate localDateTo = stringToDate(dateTo);
        LocalDate localDateFromRecord;
        builder = new StringBuilder();
        int salary;
        builder.append("Report for period ");
        builder.append(dateFrom).append(" - ");
        builder.append(dateTo);
        for (String name : names) {
            salary = 0;
            builder.append("\n").append(name).append(" - ");
            for (String record : data) {
                String[] recordArray = record.split(" ");
                localDateFromRecord = stringToDate(recordArray[0]);
                if (name.equalsIgnoreCase(recordArray[1])) {
                    if ((localDateFromRecord.isAfter(localDateFrom)
                            || localDateFromRecord.isEqual(localDateFrom))
                            && (localDateFromRecord.isBefore(localDateTo)
                            || localDateFromRecord.isEqual(localDateTo))) {
                        salary += Integer.parseInt(recordArray[2])
                                * Integer.parseInt(recordArray[3]);
                    }
                }
            }
            builder.append(salary);
        }
        return builder.toString();
    }

    private LocalDate stringToDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date;
    }
}
