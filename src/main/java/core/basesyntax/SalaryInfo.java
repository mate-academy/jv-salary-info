package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportForPeriod = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator());

        final DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate start = LocalDate.parse(dateFrom, f);
        LocalDate stop = LocalDate.parse(dateTo, f);

        int[] salaries = new int[names.length];

        for (String dat : data) {
            String[] words = dat.split(" "); // Split the data entry by space
            LocalDate current = LocalDate.parse(words[0], f); // Parse the date

            if (!current.isBefore(start) && !current.isAfter(stop)) {
                String employeeName = words[1];
                int hoursWorked = Integer.parseInt(words[2]);
                int incomePerHour = Integer.parseInt(words[3]);

                int index = -1;

                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(employeeName)) {
                        index = i;
                        break;
                    }
                }

                if (index != -1) {
                    salaries[index] += hoursWorked * incomePerHour;
                }
            }
        }

        for (int i = 0; i < names.length; i++) {
            if (i == names.length - 1) {
                reportForPeriod.append(names[i]).append(" - ").append(salaries[i]);
                break;
            }
            reportForPeriod.append(names[i]).append(" - ")
                    .append(salaries[i]).append(System.lineSeparator());
        }
        return reportForPeriod.toString();
    }
}
