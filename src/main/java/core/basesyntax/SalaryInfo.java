package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int RATE_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate fromDate;
        LocalDate toDate;
        try {
            fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
            toDate = LocalDate.parse(dateTo, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            return "Invalid date format";
        }

        int[] salaries = new int[names.length];

        for (String entry : data) {
            String[] entryParts = entry.split(" ");
            LocalDate entryDate = LocalDate.parse(entryParts[DATE_INDEX], DATE_FORMAT);
            if (!entryDate.isBefore(fromDate) && !entryDate.isAfter(toDate)) {
                String name = entryParts[NAME_INDEX];
                int hours = Integer.parseInt(entryParts[HOURS_INDEX]);
                int rate = Integer.parseInt(entryParts[RATE_INDEX]);
                int earned = hours * rate;
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(name)) {
                        salaries[i] += earned;
                        break;
                    }
                }
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator()).append((names[i])).append(" - ")
                    .append(salaries[i]);
        }
        return result.toString();

    }
}






