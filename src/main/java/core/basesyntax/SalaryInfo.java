package core.basesyntax;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE_INDEX = 3;
    private static final int NUMBER_OF_MONTH = 0;
    private static final int MONTH = 1;
    private static final int YEAR = 2;
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder resultBuilder = new StringBuilder();
        resultBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());
        String [] arrayDateFrom = dateFrom.split("\\.");
        String [] arrayDateTo = dateTo.split("\\.");
        LocalDate localDateFrom = LocalDate.of(Integer.parseInt(arrayDateFrom[YEAR]),
                Integer.parseInt(arrayDateFrom[MONTH]), Integer.parseInt(arrayDateFrom[NUMBER_OF_MONTH]));
        LocalDate localDateTo = LocalDate.of(Integer.parseInt(arrayDateTo[YEAR]),
                Integer.parseInt(arrayDateTo[MONTH]), Integer.parseInt(arrayDateTo[NUMBER_OF_MONTH]));
        for (int i = 0; i < names.length; i++) {
            int moneyEarned = 0;
            for (int j = 0;j < data.length;j++) {
            String[] parts = data[j].split(" ");
            String date = parts[DATE_INDEX];
            String [] arrayInputDate = date.split("\\.");
            LocalDate localDate = LocalDate.of(Integer.parseInt(arrayInputDate[YEAR]),
                    Integer.parseInt(arrayInputDate[MONTH]),Integer.parseInt(arrayInputDate[NUMBER_OF_MONTH]));
            String name = parts[NAME_INDEX];
            String hours = parts[HOURS_WORKED_INDEX];
            String incomePerHour = parts[HOURLY_RATE_INDEX];
                if (name.equals(names[i]) && !localDate.isAfter(localDateTo) && !localDate.isBefore(localDateFrom)) {
                moneyEarned += Integer.parseInt(hours) * Integer.parseInt(incomePerHour);
            }
        }
            resultBuilder.append(names[i]).append(" - ").append(moneyEarned).append(System.lineSeparator());

    }
        return resultBuilder.toString().trim();
}
}