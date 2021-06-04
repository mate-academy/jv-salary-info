package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {

    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final int TIME_INDEX = 0;
    public static final int HOURS_INDEX = 2;
    public static final int SALARY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder();
        LocalDate from = LocalDate.parse(dateFrom, FORMAT);
        LocalDate to = LocalDate.parse(dateTo, FORMAT);
        result.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int moneyEarned = 0;
            for (String check : data) {
                if (check.contains(name)) {
                    String[] information = check.split(" ");
                    LocalDate localDate = LocalDate.parse(information[TIME_INDEX], FORMAT);
                    if ((localDate.isAfter(from) || localDate.equals(from))
                            && (localDate.equals(to) || localDate.isBefore(to))) {
                        moneyEarned += Integer.parseInt(information[HOURS_INDEX])
                                * Integer.parseInt(information[SALARY_INDEX]);
                    }
                }
            }
            result.append("\n")
                    .append(name)
                    .append(" - ")
                    .append(moneyEarned);
        }
        return result.toString();
    }
}
