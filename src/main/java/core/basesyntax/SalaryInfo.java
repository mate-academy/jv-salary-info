package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMATTER);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        String[] parsedEmployData;
        LocalDate localEmployDate;
        for (String employName : names) {
            int employSalary = 0;
            for (String employData : data) {
                parsedEmployData = employData.split(" ");
                if (employName.equals(parsedEmployData[NAME_INDEX])) {
                    localEmployDate = LocalDate.parse(parsedEmployData[DATE_INDEX], FORMATTER);
                    if ((localEmployDate.compareTo(localDateFrom) >= 0)
                            & (localEmployDate.compareTo(localDateTo) <= 0)) {
                        employSalary += Integer.parseInt(parsedEmployData[HOURS_INDEX])
                                * Integer.parseInt(parsedEmployData[SALARY_PER_HOUR_INDEX]);
                    }
                }
            }
            report.append(System.lineSeparator()).append(employName).append(" - ")
                    .append(employSalary);
        }
        return report.toString();
    }
}
