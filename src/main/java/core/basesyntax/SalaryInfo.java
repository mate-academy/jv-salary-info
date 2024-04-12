package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate salaryDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate salaryDateTo = LocalDate.parse(dateTo, formatter);

        int johnSalary = 0;
        int andrewSalary = 0;
        int kateSalary = 0;

        for (int i = 0; i < data.length; i++) {
            String[] tempData = data[i].split(" ");
            LocalDate currentDate = LocalDate.parse(tempData[0], formatter);

            if (currentDate.isAfter(salaryDateFrom.minusDays(1))
                    && currentDate.isBefore(salaryDateTo.plusDays(1))) {
                String name = tempData[1];
                int hours = Integer.parseInt(tempData[2]);
                int incomePerHour = Integer.parseInt(tempData[3]);

                switch (name) {
                    case "John" -> johnSalary += hours * incomePerHour;
                    case "Andrew" -> andrewSalary += hours * incomePerHour;
                    case "Kate" -> kateSalary += hours * incomePerHour;
                    default -> {
                        continue;
                    }
                }
            }
        }
        String report = "Report for period " + dateFrom + " - " + dateTo
                + "\n" + names[0] + " - " + johnSalary
                + "\n" + names[1] + " - " + andrewSalary
                + "\n" + names[2] + " - " + kateSalary;

        return report;
    }
}
