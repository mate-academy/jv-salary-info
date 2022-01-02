package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder dateInfo = new StringBuilder();
        StringBuilder salaryInfo = new StringBuilder();
        dateInfo.append("Report for period ")
                .append(dateFrom)
                .append(" - ").append(dateTo)
                .append("\n");
        for (int i = 0; i < names.length; i++) {
            salaryInfo.append(names[i])
                    .append(" - ")
                    .append(salaryByName(names[i], data, dateFrom, dateTo));
            if (i != 2) {
                salaryInfo.append("\n");
            }
        }
        return dateInfo.toString() + salaryInfo.toString();
    }

    private int salaryByName(String name, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromPeriod = parseDate(dateFrom);
        LocalDate dateToPeriod = parseDate(dateTo);
        int salary = 0;
        for (String line : data) {
            String[] splitLine = line.split(" ");
            LocalDate dateCurrentLine = parseDate(splitLine[0]);
            if (name.equals(splitLine[1])
                    && isDateInInterval(dateCurrentLine, dateFromPeriod, dateToPeriod)) {
                salary += Integer.parseInt(splitLine[2]) * Integer.parseInt(splitLine[3]);
            }
        }
        return salary;
    }

    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date = LocalDate.parse(dateString, formatter);
        return date;
    }

    private Boolean isDateInInterval(LocalDate dateNow, LocalDate dateFrom, LocalDate dateTo) {
        return (dateNow.isAfter(dateFrom) || dateNow.isEqual(dateFrom))
                && (dateNow.isBefore(dateTo) || dateNow.isEqual(dateTo));
    }
}

