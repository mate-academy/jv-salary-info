package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFromString, String dateToString) {
        LocalDate dateFrom = LocalDate.parse(dateFromString, formatter);
        LocalDate dateTo = LocalDate.parse(dateToString, formatter);
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append("Report for period ").append(dateFromString)
                .append(" - ").append(dateToString);
        String[] recordValues;
        int salaryPerHour;
        int hours;
        int totalSalary;
        for (String name : names) {
            totalSalary = 0;
            for (String record : data) {
                if (record.contains(name)
                        && isDateActual(record.substring(0, 10), dateFrom, dateTo)) {
                    recordValues = record.split(" ");
                    salaryPerHour = Integer.parseInt(recordValues[2]);
                    hours = Integer.parseInt(recordValues[3]);
                    totalSalary += salaryPerHour * hours;
                }
            }
            resultMessage.append(System.lineSeparator()).append(name)
                    .append(" - ").append(totalSalary);
        }
        return resultMessage.toString();
    }

    private boolean isDateActual(String undefinedDateString, LocalDate dateFrom, LocalDate dateTo) {
        LocalDate undefinedDate = LocalDate.parse(undefinedDateString, formatter);
        return dateFrom.isBefore(undefinedDate) && undefinedDate.isBefore(dateTo)
                || undefinedDate.isEqual(dateFrom) || undefinedDate.isEqual(dateTo);
    }
}
