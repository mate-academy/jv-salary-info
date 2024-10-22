package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_FORMATTER = 0;
    private static final int NAME_FORMATTER = 1;
    private static final int WORKING_HOURS_FORMATTER = 2;
    private static final int INCOME_FORMATTER = 3;
    private static final DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DTF);
        LocalDate localDateTo = LocalDate.parse(dateTo.DTF);
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            int salary = 0;
            for (String dataInfo : data) {
                String[] info = dataInfo.split(" ");
                if (name.equals(info[NAME_FORMATTER])) {
                    LocalDate recordDate = LocalDate.parse(info[DATE_FORMATTER], DTF);
                    if (!recordDate.isBefore(from) && !recordDate.isAfter(to)) {
                        salary += Integer.parseInt(info[INCOME_FORMATTER])
                                * Integer.parseInt(info[WORKING_HOURS_FORMATTER]);
                    }
                }
            }
            salaryInfo.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(salary);
        }

        for (String currentName : names) {
            int salaryAmount = calculateSalaryForEmployee(currentName, data, dateFrom, dateTo);
            report.append(System.lineSeparator())
                    .append(currentName)
                    .append(" - ")
                    .append(salaryAmount);
        }

        return salaryInfo.toString();
    }
}
