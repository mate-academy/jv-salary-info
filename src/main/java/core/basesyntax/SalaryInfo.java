package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate fromDate;
        LocalDate toDate;
        try {
            fromDate = LocalDate.parse(dateFrom, dateFormat);
            toDate = LocalDate.parse(dateTo, dateFormat);
        } catch (DateTimeParseException e) {
            return "Invalid date format";
        }

        int[] salaries = new int[names.length];

        for (String entry : data) {
            String[] entryParts = entry.split(" ");
            if (entryParts.length == 4) {
                LocalDate entryDate = LocalDate.parse(entryParts[0], dateFormat);
                if (!entryDate.isBefore(fromDate) && !entryDate.isAfter(toDate)) {
                    String name = entryParts[1];
                    int hours = Integer.parseInt(entryParts[2]);
                    int rate = Integer.parseInt(entryParts[3]);
                    int earned = hours * rate;

                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(name)) {
                            salaries[i] += earned;
                            break;
                        }
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






