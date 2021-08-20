package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            int total = 0;
            for (int j = 0; j < data.length; j++) {
                if (data[j].contains(names[i])) {
                    String[] splitData = data[j].split(" ");
                    LocalDate fromDate = LocalDate.parse(dateFrom, DATE_FORMAT);
                    LocalDate employeeDate = LocalDate.parse(splitData[0], DATE_FORMAT);
                    LocalDate toDate = LocalDate.parse(dateTo, DATE_FORMAT);
                    if (employeeDate.isAfter(fromDate) && employeeDate.isBefore(toDate)
                            || employeeDate.isEqual(fromDate) || employeeDate.isEqual(toDate)) {
                        int workHrs = Integer.parseInt(splitData[2]);
                        int payment = Integer.parseInt(splitData[3]);
                        total += workHrs * payment;
                    }
                }
            }
            report.append(System.lineSeparator()).append(names[i]).append(" - ").append(total);
        }
        return report.toString();
    }
}
