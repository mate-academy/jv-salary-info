package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        if (names == null || data == null) {
            return "No data";
        }
        String[] partsOfData;
        String dataName;
        int workingHour;
        int incomePerHour;
        int calcSalary = 0;

        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            for (String datum : data) {
                if (datum == null) {
                    continue;
                }
                partsOfData = datum.split(" ");
                dataName = partsOfData[1];
                workingHour = Integer.parseInt(partsOfData[2]);
                incomePerHour = Integer.parseInt(partsOfData[3]);
                LocalDate localDate = LocalDate.parse(partsOfData[0], FORMATTER);
                if (name.equals(dataName)
                        && !localDate.isBefore(localDateFrom)
                        && !localDate.isAfter(localDateTo)) {
                    calcSalary = calcSalary + (workingHour * incomePerHour);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(calcSalary);
            calcSalary = 0;
        }
        return stringBuilder.toString();
    }
}

