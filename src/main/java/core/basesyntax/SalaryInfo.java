package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, formatter);
        LocalDate toDate = LocalDate.parse(dateTo, formatter);
        StringBuilder builderPeriod = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo);
        StringBuilder builderSalaries = new StringBuilder();
        for (String name : names) {
            int salary = 0;
            for (String dataRecord : data) {
                String[] dataRecordArray = dataRecord.split(" ");
                if (dataRecordArray[1].equals(name)) {
                    if ((LocalDate.parse(dataRecordArray[0], formatter).isAfter(fromDate)
                            || LocalDate.parse(dataRecordArray[0], formatter).isEqual(fromDate))
                            && (LocalDate.parse(dataRecordArray[0], formatter).isBefore(toDate)
                            || LocalDate.parse(dataRecordArray[0], formatter).isEqual(toDate))) {
                        salary = salary + Integer.parseInt(dataRecordArray[2])
                                * Integer.parseInt(dataRecordArray[3]);
                    }
                }
            }
            builderSalaries.append(System.lineSeparator())
                        .append(name).append(" - ")
                        .append(salary);
        }
        return builderPeriod.append(builderSalaries).toString();
    }
}
