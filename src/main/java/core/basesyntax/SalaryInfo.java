package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder report = new StringBuilder("Report for period " + dateFrom + " - " + dateTo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate numberDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate numberDateTo = LocalDate.parse(dateTo, formatter);

        for (String name : names) {
            int totalSalary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] dataDividing = data[i].split(" ");
                LocalDate givenDay = LocalDate.parse(dataDividing[0], formatter);
                if (givenDay.isAfter(numberDateFrom) && givenDay
                        .isBefore(numberDateTo.plusDays(1)) && dataDividing[1].equals(name)) {
                    totalSalary += Integer.parseInt(dataDividing[2]) * Integer
                            .parseInt(dataDividing[3]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(totalSalary);
        }
        return report.toString();
    }
}
