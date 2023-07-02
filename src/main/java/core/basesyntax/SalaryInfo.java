package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate;
        LocalDate endDate;
        startDate = LocalDate.parse(dateFrom, dateFormatter);
        endDate = LocalDate.parse(dateTo, dateFormatter);
        reportBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());
        for (String name : names) {
            int totalSalary = 0;
            for (String entryData : data) {
                String[] dataParts = entryData.split(" ");
                String dateStr = dataParts[0];
                String employeeName = dataParts[1];
                int employeeWorkedHours = Integer.parseInt(dataParts[2]);
                int employeeIncomePerHour = Integer.parseInt(dataParts[3]);
                LocalDate entryDateByUserName = LocalDate.parse(dateStr, dateFormatter);
                if (entryDateByUserName.isEqual(startDate)
                        || entryDateByUserName.isAfter(startDate)) {
                    if (entryDateByUserName.isBefore(endDate)
                            || entryDateByUserName.isEqual(endDate)) {
                        if (employeeName.equals(name)) {
                            totalSalary += employeeWorkedHours * employeeIncomePerHour;
                        }
                    }
                }
            }
            reportBuilder.append(name)
                    .append(" - ")
                    .append(totalSalary)
                    .append(System.lineSeparator());
        }
        return reportBuilder.toString().trim();
    }
}


