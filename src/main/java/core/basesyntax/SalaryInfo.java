package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int INDEX_OF_DATE = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_AMOUNT = 3;
    private static final DateTimeFormatter dateFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        int amountSum = 0;
        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            for (String information : data) {
                if (compareDates(information, dateFrom, dateTo)
                        && compareNames(information, name)) {
                    amountSum += getDaySalary(information);
                }
            }
            builder.append("\n")
                    .append(name)
                    .append(" - ")
                    .append(amountSum);
            amountSum = 0;
        }
        return builder.toString();
    }

    private int getDaySalary(String information) {
        return Integer.parseInt(getInfoByIndex(information, INDEX_OF_HOURS))
                * Integer.parseInt(getInfoByIndex(information, INDEX_OF_AMOUNT));
    }

    private boolean compareNames(String information, String name) {
        String nameInfo = getInfoByIndex(information, INDEX_OF_NAME);
        return nameInfo.equals(name);
    }

    private boolean compareDates(String information, String dateFrom, String dateTo) {
        String dateInfo = getInfoByIndex(information, INDEX_OF_DATE);
        LocalDate currentDate = LocalDate.parse(dateInfo, dateFormatter);
        LocalDate startDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate endDate = LocalDate.parse(dateTo, dateFormatter);
        return currentDate.isAfter(startDate) && currentDate.isBefore(endDate)
                || currentDate.isAfter(startDate) && currentDate.equals(endDate);
    }

    private String getInfoByIndex(String information, int index) {
        String[] totalInformation = information.split(" ");
        return totalInformation[index];
    }
}
