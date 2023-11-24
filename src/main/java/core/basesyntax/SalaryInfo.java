package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int WORKED_HOURS = 2;
    private static final int SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate dateStart = null;
        LocalDate dateStop = null;
        String reportDates = "Report for period " + dateFrom + " - " + dateTo;
        StringBuilder persons = new StringBuilder();

        try {
            dateStart = LocalDate.parse(dateFrom, FORMATTER);
            dateStop = LocalDate.parse(dateTo, FORMATTER);
        } catch (Exception e) {
            System.out.println("Wrong Date Format");
        }

        String recordDateStr = null;
        int hours = 0;
        int rate = 0;
        int salary = 0;
        for (String name : names) {
            if (salary > 0) {
                salary = 0;
            }
            for (String datum : data) {
                try {
                    String[] stringParts = datum.split(" ");
                    recordDateStr = stringParts[DATE];
                    hours = Integer.parseInt(stringParts[WORKED_HOURS]);
                    rate = Integer.parseInt(stringParts[SALARY_PER_HOUR]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Not enough information in string");
                }
                LocalDate date = LocalDate.parse(recordDateStr, FORMATTER);
                boolean daysStart = date.isAfter(dateStart);
                boolean daysEnd = date.isBefore(dateStop.plusDays(1));
                if ((datum.contains(name)) & (daysStart & daysEnd)) {
                    salary += (hours * rate);
                }
            }
            persons.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportDates + persons;
    }
}
