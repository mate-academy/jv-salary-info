package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder resultListBuilder = new StringBuilder("Report for period "
                + dateFrom
                + " - "
                + dateTo
                + System.lineSeparator());
        LocalDate firstDate = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate secondDate = LocalDate.parse(dateTo, dateFormatter);

        for (String name : names) {
            int totalSalary = calculateTotalSalary(data, firstDate, secondDate, name);
            resultListBuilder
                    .append(name)
                    .append(" - ")
                    .append(totalSalary)
                    .append(System.lineSeparator());
        }

        String result = resultListBuilder.toString();
        return removeTrailingLineSeparator(result);
    }

    private int calculateTotalSalary(String[] data, LocalDate firstDate,
                                     LocalDate secondDate, String name) {
        int totalSalary = 0;
        for (String element : data) {
            String[] parts = element.split(" ");
            LocalDate workDate = LocalDate.parse(parts[0], dateFormatter);
            String employeeName = parts[1];
            int workingHours = Integer.parseInt(parts[2]);
            int moneyPerHourEarned = Integer.parseInt(parts[3]);

            if (isWithinDateRange(workDate, firstDate, secondDate) && name.equals(employeeName)) {
                totalSalary += moneyPerHourEarned * workingHours;
            }
        }
        return totalSalary;
    }

    private boolean isWithinDateRange(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    private String removeTrailingLineSeparator(String result) {
        if (result.isEmpty()) {
            return result;
        }
        return result.substring(0, result.length() - System.lineSeparator().length());
    }
}
