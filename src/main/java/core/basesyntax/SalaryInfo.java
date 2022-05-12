package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate = LocalDate.parse(dateFrom, form);
        LocalDate toDate = LocalDate.parse(dateTo, form);
        for (String name : names) {
            int salary = 0;
            for (String dat : data) {
                String[] splitData = dat.split(" ");
                if (name.equals(splitData[1])) {
                    LocalDate dataDate = LocalDate.parse(splitData[0], form);
                    if ((dataDate.isEqual(fromDate) || dataDate.isAfter(fromDate))
                            && (dataDate.isEqual(toDate) || dataDate.isBefore(toDate))) {
                        salary += Integer.valueOf(splitData[2])
                                * Integer.valueOf(splitData[3]);
                    }
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
