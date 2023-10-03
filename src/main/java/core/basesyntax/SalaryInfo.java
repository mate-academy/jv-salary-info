package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private LocalDate getLocalDate(String date) {
        return LocalDate.parse(date, formatter);
    }

    private boolean isValidDate(LocalDate startDate, LocalDate endDate, String checkedDate) {
        LocalDate localDate = getLocalDate(checkedDate);
        return localDate.isAfter(startDate.minusDays(1))
            && localDate.isBefore(endDate.plusDays(1));
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SalaryInfo salaryInfo = new SalaryInfo();
        LocalDate startLocalDate = salaryInfo.getLocalDate(dateFrom);
        LocalDate endLocalDate = salaryInfo.getLocalDate(dateTo);
        int[] totalSalaries = new int[names.length];

        for (String datum : data) {
            String[] splittedData = datum.split(" ");
            String date = splittedData[0];
            String name = splittedData[1];
            int workingHours = Integer.parseInt(splittedData[2]);
            int hourSalary = Integer.parseInt(splittedData[3]);
            for (int i = 0; i < names.length; i++) {
                if (names[i].equals(name) && salaryInfo.isValidDate(startLocalDate,
                        endLocalDate, date)) {
                    totalSalaries[i] += workingHours * hourSalary;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder(
                "Report for period " + dateFrom + " - " + dateTo);
        for (int i = 0; i < names.length; i++) {
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(names[i]).append(" - ").append(totalSalaries[i]);
        }
        return stringBuilder.toString();
    }
}

