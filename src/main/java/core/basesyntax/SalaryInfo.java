package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String COMMA = ",";
    public static final String SPACE = " ";
    public static final int FIRST_INDEX = 0;
    public static final int SECOND_INDEX = 1;
    public static final int THIRD_INDEX = 2;
    public static final int FORTH_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate fromDate = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate toDate = LocalDate.parse(dateTo, FORMATTER);
        for (String name : names) {
            int currentSalary = 0;
            for (String currentData : data) {
                String[] splitValues = currentData.split(" ");
                LocalDate currentDate = LocalDate.parse(splitValues[FIRST_INDEX], FORMATTER);
                String currentName = splitValues[SECOND_INDEX];
                int hoursCounter = Integer.parseInt(splitValues[THIRD_INDEX]);
                int cashPerHour = Integer.parseInt(splitValues[FORTH_INDEX]);

                if (currentName.equals(name)
                        && (currentDate.equals(fromDate) || currentDate.isAfter(fromDate))
                        && (currentDate.equals(toDate) || currentDate.isBefore(toDate))) {
                    currentSalary += hoursCounter * cashPerHour;
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(currentSalary);
        }
        return builder.toString();
    }
}
