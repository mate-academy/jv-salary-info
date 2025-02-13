package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_FORMAT);
        LocalDate endDate = LocalDate.parse(dateTo, DATE_FORMAT);

        int[] salaries = new int[names.length]; // Używamy tablicy zamiast HashMap

        for (String record : data) {
            String[] splitData = record.split(" ");
            LocalDate workDate = LocalDate.parse(splitData[0], DATE_FORMAT);
            String employeeName = splitData[1];
            int hoursWorked = Integer.parseInt(splitData[2]);
            int hourlyRate = Integer.parseInt(splitData[3]);

            if (!workDate.isBefore(startDate) && !workDate.isAfter(endDate)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        salaries[i] += hoursWorked * hourlyRate;
                        break; // Znaleziono pracownika, więc kończymy pętlę
                    }
                }
            }
        }

        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator()) // Zamiast \n
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }

        return report.toString();
    }
}
