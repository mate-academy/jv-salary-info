package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatDate);
        LocalDate endDate = LocalDate.parse(dateTo, formatDate);
        StringBuilder stringPeriod = new StringBuilder();
        stringPeriod.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int resultSalary = 0;
            for (String rowData : data) {
                String[] rowSplit = rowData.split(" ");
                if (name.equals(rowSplit[1])) {
                    LocalDate dateFromData = LocalDate.parse(rowSplit[0], formatDate);
                    if ((dateFromData.isAfter(startDate) || dateFromData.isEqual(startDate))
                            && (dateFromData.isBefore(endDate) || dateFromData.isEqual(endDate))) {
                        resultSalary += Integer.parseInt(rowSplit[2])
                                * Integer.parseInt(rowSplit[3]);
                    }
                }
            }
            stringPeriod.append(System.lineSeparator()).append(name).append(" - ")
                    .append(resultSalary);
        }
        return stringPeriod.toString();
    }
}
