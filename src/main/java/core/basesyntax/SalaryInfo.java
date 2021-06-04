package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int INCOME_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder resultMessage = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int sumSalary = 0;
            for (String currentData : data) {
                String[] splitData = currentData.split(" ");
                if (splitData[NAME_INDEX].equals(name) && isBetween(getDateFromString(dateFrom),
                        getDateFromString(dateTo),
                        getDateFromString(splitData[DATE_INDEX]))) {
                    sumSalary += Integer.parseInt(splitData[WORKING_HOUR_INDEX])
                            * Integer.parseInt(splitData[INCOME_INDEX]);
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

    private LocalDate getDateFromString(String date) {
        return LocalDate.parse(date, DATE_FORMAT);
    }

    private boolean isBetween(LocalDate startData, LocalDate endData, LocalDate currentDate) {
        return currentDate.isBefore(endData)
                && currentDate.isAfter(startData)
                || startData.equals(currentDate)
                || endData.equals(currentDate);
    }
}
