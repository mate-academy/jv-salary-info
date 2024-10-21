package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder resultBuilder = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            resultBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ");
            int totalSalary = 0;
            for (String lineData : data) {
                String[] arrData = lineData.split(" ");
                String nameData = arrData[1];
                if (name.equals(nameData)) {
                    LocalDate dateData = LocalDate.parse(arrData[0], FORMATTER);
                    if (dateData.compareTo(localDateFrom) >= 0
                            && dateData.compareTo(localDateTo) <= 0) {
                        totalSalary += Integer.valueOf(arrData[2]) * Integer.valueOf(arrData[3]);
                    }
                }
            }
            resultBuilder.append(totalSalary);
        }

        return resultBuilder.toString();
    }
}
