package core.basesyntax;

import java.time.LocalDate;
public class SalaryInfo {
    static final int DATE_INDEX = 0;
    static final int NAME_INDEX = 1;
    static final int HOURS_INDEX = 2;
    static final int RATE_INDEX = 3;
    static final int DAY_INDEX = 0;
    static final int MONTH_INDEX = 1;
    static final int YEAR_INDEX = 2;
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate locFrom = getLocalDate(dateFrom);
        LocalDate locTo = getLocalDate(dateTo);
        for (String name : names) {
            sb.append(System.lineSeparator());
            int total = 0;
            for (String piece : data) {
                String currentDate = piece.split(" ")[DATE_INDEX];
                LocalDate loc = getLocalDate(currentDate);
                String currentName = piece.split(" ")[NAME_INDEX];
                int hours = Integer.parseInt(piece.split(" ")[HOURS_INDEX]);
                int perHour = Integer.parseInt(piece.split(" ")[RATE_INDEX]);
                if (currentName.equals(name) && !loc.isBefore(locFrom) && !loc.isAfter(locTo)) {
                    total += hours * perHour;
                }
            }
            sb.append(name).append(" - ").append(total);
        }
        return sb.toString();
    }

    public LocalDate getLocalDate(String date) {
        String[] d = date.split("\\.");
        int year = Integer.parseInt(d[YEAR_INDEX]);
        int month = Integer.parseInt(d[MONTH_INDEX]);
        int day = Integer.parseInt(d[DATE_INDEX]);
        return LocalDate.of(year, month, day);
    }
}
