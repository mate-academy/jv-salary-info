package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder resultMessage = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int sumSalary = 0;
            for (String currentData : data) {
                String[] splitData = currentData.split(" ");
                if (splitData[1].equals(name) && isBetween(getDateFromString(dateFrom),
                        getDateFromString(dateTo),
                        getDateFromString(splitData[0]))) {
                    sumSalary += Integer.parseInt(splitData[2]) * Integer.parseInt(splitData[3]);
                }
            }
            resultMessage
                    .append("\n")
                    .append(name)
                    .append(" - ")
                    .append(sumSalary);
        }
        return resultMessage.toString();
    }

    public LocalDate getDateFromString(String date) {
        return LocalDate.parse(date, DATE_FORMAT);
    }

    public boolean isBetween(LocalDate startData, LocalDate endData, LocalDate currentDate) {
        return currentDate.isBefore(endData)
                && currentDate.isAfter(startData)
                || startData.equals(currentDate)
                || endData.equals(currentDate);
    }
}
