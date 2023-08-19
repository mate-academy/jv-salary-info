package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom.trim(), formatter);
        LocalDate endDate = LocalDate.parse(dateTo.trim(), formatter);
        StringBuilder report = new StringBuilder();
        String reportTitle = String.format("Report for period %s - %s",
                                            startDate.format(formatter),
                                            endDate.format(formatter));
        report.append(reportTitle);
        LocalDate reportDate;

        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator()).append(names[i]).append(" - ");
            int currentWorkerSalary = 0;
            for (int j = 0; j < data.length; j++) {
                String [] reportLine = data[j].split(" ");
                reportDate = LocalDate.parse(reportLine[0], formatter);
                if (reportLine[1].equals(names[i])
                        && (reportDate.isAfter(startDate) || reportDate.isEqual(startDate))
                        && (reportDate.isBefore(endDate) || reportDate.isEqual(endDate))) {
                    currentWorkerSalary += Integer.parseInt(reportLine[2])
                                         * Integer.parseInt(reportLine[3]);
                }
            }
            report.append(currentWorkerSalary);
        }
        return report.toString();
    }
}
