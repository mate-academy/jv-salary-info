package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int PARTICULAR_DAY = 0;
    private static final int NAME_OF_EMPLOYEE = 1;
    private static final int NUMBER_OF_HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);

        for (String name : names) {
            int income = 0;
            for (String checkData : data) {
                String[] splitData = checkData.split(" ");
                LocalDate currentDate = LocalDate.parse(splitData[PARTICULAR_DAY],
                        FORMATTER);
                String nameOfEmployee = splitData[NAME_OF_EMPLOYEE];
                int numberOfHours = Integer.parseInt(splitData[NUMBER_OF_HOURS]);
                int incomePerHour = Integer.parseInt(splitData[INCOME_PER_HOUR]);
                LocalDate start = LocalDate.parse(dateFrom, FORMATTER);
                LocalDate finish = LocalDate.parse(dateTo, FORMATTER);
                if (name.equals(nameOfEmployee)
                        && (currentDate.isAfter(start)
                        || currentDate.equals(start))
                        && (currentDate.isBefore(finish)
                        || currentDate.equals(finish))) {
                    income += numberOfHours * incomePerHour;
                }
            }
            stringBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(income);
        }
        return stringBuilder.toString();
    }
}
