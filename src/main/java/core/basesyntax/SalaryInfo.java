package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int totalSalary = 0;
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ").append(dateTo);
        if (names != null && data != null) {
            for (String name : names) {
                for (String dataStr : data) {
                    if (compareNames(name, getName(dataStr))
                            && isDateBetween(toDate(dataStr),toDate(dateFrom),toDate(dateTo))) {
                        totalSalary += getHoursInDay(dataStr) * getPayment(dataStr);
                    }
                }
                builder.append("\n").append(name).append(" - ").append(totalSalary);
                totalSalary = 0;
            }
        }
        return builder.toString();
    }

    public LocalDate toDate(String dateInString) {
        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("dd.MM.yyyy");
            return LocalDate.parse(dateInString.split(" ")[0], formatter);
        } catch (DateTimeParseException exc) {
            System.out.println(dateInString.split(" ")[0] + "is not parsable!");
            throw exc;
        }
    }

    public String getName(String data) {
        return data.split(" ")[1];
    }

    public int getHoursInDay(String data) {
        return Integer.parseInt(data.split(" ")[2]);
    }

    public int getPayment(String data) {
        return Integer.parseInt(data.split(" ")[3]);
    }

    public boolean compareNames(String dataName, String name) {
        return dataName.equals(name);
    }

    public boolean isDateBetween(LocalDate date, LocalDate dateFrom, LocalDate dateTo) {
        return (!date.isBefore(dateFrom) && !date.isAfter(dateTo));
    }
}
