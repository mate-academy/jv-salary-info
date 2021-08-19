package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ");
        salaryInfo.append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int totalSalary = 0;
            for (String line : data) {
                if (line.contains(name)) {
                    String[] lineElements = line.split("\\s");
                    if (dateCompare(dateFrom, dateTo, lineElements[0])) {
                        int workingHours = Integer.parseInt(lineElements[2]);
                        int ratePerHour = Integer.parseInt(lineElements[3]);
                        totalSalary += workingHours * ratePerHour;
                    }
                }
            }
            salaryInfo.append(System.lineSeparator());
            salaryInfo.append(name).append(" - ").append(totalSalary);
        }
        return salaryInfo.toString();
    }

    public boolean dateCompare(String dateFrom, String dateTo, String actualDate) {
        return (getFormattedDate(actualDate).isEqual(getFormattedDate(dateTo))
                || getFormattedDate(actualDate).isEqual(getFormattedDate(dateFrom)))
                || (getFormattedDate(actualDate).isBefore(getFormattedDate(dateTo))
                && getFormattedDate(actualDate).isAfter(getFormattedDate(dateFrom)));
    }

    public LocalDate getFormattedDate(String date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, dateTimeFormatter);
    }
}
