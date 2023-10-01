package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public LocalDate getLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(date, formatter);
    }

    public boolean isValidDate(LocalDate startDate, LocalDate endDate, String currentDate) {
        LocalDate localDate = new SalaryInfo().getLocalDate(currentDate);
        return (localDate.isAfter(startDate.minusDays(1))
            && localDate.isBefore(endDate.plusDays(1)));
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SalaryInfo salaryInfo = new SalaryInfo();
        LocalDate startLocalDate = salaryInfo.getLocalDate(dateFrom);
        LocalDate endLocalDate = salaryInfo.getLocalDate(dateTo);
        int[] totalSalaries = new int[names.length];

        for (String datum : data) {
            String[] splittedData = datum.split(" ");
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(splittedData[1]) && salaryInfo.isValidDate(startLocalDate,
                        endLocalDate, splittedData[0])) {
                    totalSalaries[i] +=
                        Integer.parseInt(splittedData[2]) * Integer.parseInt(splittedData[3]);
                }
            }
        }

        StringBuilder sb = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            sb.append(System.lineSeparator());
            sb.append(names[i]).append(" - ").append(totalSalaries[i]);
        }
        return sb.toString();
    }
}

