package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int HOUR_INCOME_INDEX = 3;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startLocalDate = parseDate(dateFrom);
        LocalDate endLocalDate = parseDate(dateTo);
        int[] totalSalaries = new int[names.length];
        int workingHours;
        int hourSalary;
        for (String datum : data) {
            String[] splittedData = datum.split(" ");
            String date = splittedData[DATE_INDEX];
            String name = splittedData[NAME_INDEX];
            workingHours = Integer.parseInt(splittedData[HOURS_INDEX]);
            hourSalary = Integer.parseInt(splittedData[HOUR_INCOME_INDEX]);
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(name) && isValidDate(startLocalDate,
                        endLocalDate, date)) {
                    totalSalaries[i] += workingHours * hourSalary;
                }
            }
        }
        return reportGenerator(names, totalSalaries, dateFrom, dateTo);
    }

    private LocalDate parseDate(String date) {
        return LocalDate.parse(date, formatter);
    }

    private boolean isValidDate(LocalDate startDate, LocalDate endDate, String checkedDate) {
        LocalDate localDate = parseDate(checkedDate);
        return localDate.isAfter(startDate)
                && localDate.isBefore(endDate.plusDays(1));
    }

    private String reportGenerator(String[] names, int[] totalSalaries,
                                   String dateFrom, String dateTo) {
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(names[i]).append(" - ").append(totalSalaries[i]);
        }
        return stringBuilder.toString();
    }
}
