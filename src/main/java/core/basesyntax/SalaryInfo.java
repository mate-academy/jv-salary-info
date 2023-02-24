package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        boolean daysStart;
        boolean daysEnd;
        LocalDate dateStartCount = null;
        LocalDate dateStopCount = null;
        int salaryForOneDay;
        int salary = 0;
        String name;

        String reportDates = "Report for period " + dateFrom + " - " + dateTo;
        StringBuilder persons = new StringBuilder();

        try {
            dateStartCount = LocalDate.parse(dateFrom, formatter);
            dateStopCount = LocalDate.parse(dateTo, formatter);
        } catch (Exception e) {
            System.out.println("Wrong Date Format");
        }

        String recordDateStr = null;
        int hours = 0;
        int rate = 0;
        for (String s : names) {
            name = s;
            if (salary > 0) {
                salary = 0;
            }
            for (String datum : data) {
                try {
                    String[] stringParts = datum.split(" ");
                    recordDateStr = stringParts[0];
                    hours = Integer.parseInt(stringParts[2]);
                    rate = Integer.parseInt(stringParts[3]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Not enough information in string");
                }
                LocalDate getDateFromString = LocalDate.parse(recordDateStr, formatter);
                daysStart = getDateFromString.isAfter(dateStartCount);
                daysEnd = getDateFromString.isBefore(dateStopCount.plusDays(1));

                if ((datum.contains(name)) & (daysStart & daysEnd)) {
                    salaryForOneDay = hours * rate;
                    salary += salaryForOneDay;
                }
            }
            persons.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return reportDates + persons;
    }
}
