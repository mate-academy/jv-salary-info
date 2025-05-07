package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);

        int[] employeesSalaries = new int[names.length];

        for (String record : data) {
            String[] partsOfData = record.split(" ");
            String dataDate = partsOfData[0];
            LocalDate currentDate = LocalDate.parse(dataDate, formatter);
            String dataName = partsOfData[1];
            int dataHoursWorked = Integer.parseInt(partsOfData[2]);
            int dataRatePerHour = Integer.parseInt(partsOfData[3]);

            if (currentDate.isEqual(startDate) || currentDate.isAfter(startDate)) {
                if (currentDate.isEqual(endDate) || currentDate.isBefore(endDate)) {
                    for (int i = 0; i < names.length; i++) {
                        if (names[i].equals(dataName)) {
                            employeesSalaries[i] += dataHoursWorked * dataRatePerHour;
                        }
                    }
                }
            }
        }

        StringBuilder salaryInfoReport = new StringBuilder();
        salaryInfoReport.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            salaryInfoReport.append(names[i])
                    .append(" - ")
                    .append(employeesSalaries[i]);
            if (i < names.length - 1) {
                salaryInfoReport.append(System.lineSeparator());
            }
        }
        return salaryInfoReport.toString();
    }
}
