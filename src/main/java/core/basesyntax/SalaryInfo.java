package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        int employSalary;
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        String[] parsedEmployData;
        LocalDate localEmployDate;
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String employName : names) {
            employSalary = 0;
            for (String employData : data) {
                parsedEmployData = employData.split(" ");
                if (employName.equals(parsedEmployData[1])) {
                    localEmployDate = LocalDate.parse(parsedEmployData[0], FORMATTER);
                    if ((localEmployDate.compareTo(localDateFrom) >= 0)
                            & (localEmployDate.compareTo(localDateTo) <= 0)) {
                        employSalary += Integer.parseInt(parsedEmployData[2])
                                * Integer.parseInt(parsedEmployData[3]);
                    }
                }
            }
            report.append(System.lineSeparator()).append(employName).append(" - ").append(employSalary);
        }
        return report.toString();
    }
}
