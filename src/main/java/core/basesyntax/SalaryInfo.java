package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_POSITION_IN_DATA_STRING = 0;
    private static final int NAME_POSITION_IN_DATA_STRING = 1;
    private static final int HOURS_POSITION_IN_DATA_STRING = 2;
    private static final int INCOME_PER_HOUR_POSITION_IN_DATA_STRING = 3;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder();
        sb.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int j = 0; j < names.length; j++) {
            int salaryAmount = 0;

            for (int i = 0; i < data.length; i++) {
                String[] dataString = data[i].split(" ");
                LocalDate date = LocalDate.parse(dataString[DATE_POSITION_IN_DATA_STRING],
                        dateFormatter);

                if (isDateBetweenDates(date, dateFrom, dateTo)
                        && names[j].equals(dataString[NAME_POSITION_IN_DATA_STRING])) {

                    salaryAmount += Integer.parseInt(dataString[HOURS_POSITION_IN_DATA_STRING])
                            * Integer.parseInt(dataString[INCOME_PER_HOUR_POSITION_IN_DATA_STRING]);
                }
            }
            sb.append(System.lineSeparator())
                    .append(names[j])
                    .append(" - ")
                    .append(salaryAmount);
        }
        return sb.toString();
    }

    private boolean isDateBetweenDates(LocalDate date, String dateFrom, String dateTo) {
        return date.isAfter(LocalDate.parse(dateFrom, dateFormatter).minusDays(1))
                && date.isBefore(LocalDate.parse(dateTo, dateFormatter).plusDays(1));
    }
}
