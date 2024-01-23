package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int INDEX_DATE = 0;
    private static final int INDEX_NAME = 1;
    private static final int INDEX_HOURS = 2;
    private static final int INDEX_INCOME = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ");
        builder.append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                String[] currentData = datum.split(" ");
                int hours = Integer.parseInt(currentData[INDEX_HOURS]);
                int income = Integer.parseInt(currentData[INDEX_INCOME]);
                LocalDate dateLocal = LocalDate.parse(currentData[INDEX_DATE], FORMATTER);
                LocalDate dateFromLocal = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate dateToLocal = LocalDate.parse(dateTo, FORMATTER);
                try {
                    if (currentData[INDEX_NAME].equals(name)) {
                        if (getMonth(dateFromLocal) < getMonth(dateToLocal)
                                && getMonth(dateLocal) == getMonth(dateFromLocal)
                                && getDay(dateLocal) >= getDay(dateFromLocal)
                                || getMonth(dateLocal) == getMonth(dateToLocal)
                                && getDay(dateLocal) <= getDay(dateToLocal)
                                || getDay(dateLocal) >= getDay(dateFromLocal)
                                && getDay(dateLocal) <= getDay(dateToLocal)) {
                            salary += hours * income;
                        }
                    } else {
                        throw new NoSuchNameException("No such name");
                    }
                } catch (NoSuchNameException e) {
                    continue;
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }

    public int getDay(LocalDate date) {
        return date.getDayOfMonth();
    }

    public int getMonth(LocalDate date) {
        return date.getMonthValue();
    }
}
