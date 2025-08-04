package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);

        for (String worker : names) {
            int salary = 0;

            for (String dataIndex : data) {
                String[] dataArray = dataIndex.split(" ");
                String dataStr = dataArray[0];
                String workerName = dataArray[1];
                String workHour = dataArray[2];
                String salaryPerHour = dataArray[3];

                LocalDate workingDay = LocalDate.parse(dataStr, formatter);

                if ((worker.equals(workerName))
                        && (!workingDay.isBefore(startDate))
                        && (!workingDay.isAfter(endDate))) {
                    salary += Integer.parseInt(workHour) * Integer.parseInt(salaryPerHour);
                }
            }
            report.append(worker)
                    .append(" - ")
                    .append(salary);

            if (!worker.equals(names[names.length - 1])) {
                report.append(System.lineSeparator());
            }
        }

        return report.toString();
    }
}
