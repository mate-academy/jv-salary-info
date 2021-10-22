package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_FOR_DATE = 0;
    private static final int INDEX_FOR_NAME = 1;
    private static final int INDEX_FOR_HOURS = 2;
    private static final int INDEX_FOR_PRICE = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate localDateTo = LocalDate.parse(dateTo, DATE_FORMAT);
        StringBuilder builder = new StringBuilder();
        for (String name : names) {
            int salary = 0;
            for (String string : data) {
                String[] information = string.split(" ");
                LocalDate date = LocalDate.parse(information[INDEX_FOR_DATE], DATE_FORMAT);
                String employeeName = information[INDEX_FOR_NAME];
                int amountHours = Integer.parseInt(information[INDEX_FOR_HOURS]);
                int pricePerHour = Integer.parseInt(information[INDEX_FOR_PRICE]);
                if (date.isBefore(localDateTo.plusDays(1))
                        && date.isAfter(localDateFrom.minusDays(1)) && employeeName.equals(name)) {
                    salary += amountHours * pricePerHour;
                }
            }
            builder.append(name).append(" - ").append(salary).append(System.lineSeparator());
        }
        return "Report for period " + dateFrom + " - " + dateTo + System.lineSeparator()
                + builder.toString().trim();
    }
}
