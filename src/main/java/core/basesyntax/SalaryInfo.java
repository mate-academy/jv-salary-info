package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);
        StringBuilder salaryReport = new StringBuilder();
        salaryReport.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());
        for (String name : names) {
            int salaryTotal = 0;
            for (String record : data) {
                String[] partOfRecord = record.split(" ");
                LocalDate workingDay = LocalDate.parse(partOfRecord[0], formatter);
                String employeeName = partOfRecord[1];
                int workingHours = Integer.parseInt(partOfRecord[2]);
                int salaryPerHour = Integer.parseInt(partOfRecord[3]);
                if (employeeName.equals(name) && !workingDay.isBefore(dateStart) && !workingDay.isAfter(dateEnd)) {
                    salaryTotal += workingHours * salaryPerHour;
                }
            }
            salaryReport.append(name).append(" - ").append(salaryTotal).append(System.lineSeparator());
        }
        return salaryReport.toString();
    }
}
