package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String DATA_SEPARATOR = " ";
    public static final int DATE_INDEX_DATA_RECORD = 0;
    public static final int HOURS_INDEX_DATA_RECORD = 2;
    public static final int RATE_INDEX_DATA_RECORD = 3;
    public static final String REPORT_SEPARATOR = " - ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int salaryPerEmployee = 0;
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(REPORT_SEPARATOR).append(dateTo);
        for (String name : names) {
            for (String record : data) {
                if (record.contains(name)) {
                    String[] recordArray = record.split(DATA_SEPARATOR);
                    LocalDate localDateWork
                            = LocalDate.parse(recordArray[DATE_INDEX_DATA_RECORD], formatter);
                    if (localDateWork.isEqual(localDateFrom)
                            || localDateWork.isEqual(localDateTo)
                            || localDateWork.isAfter(localDateFrom)
                            && localDateWork.isBefore(localDateTo)) {
                        salaryPerEmployee += Integer.parseInt(recordArray[HOURS_INDEX_DATA_RECORD])
                                * Integer.parseInt(recordArray[RATE_INDEX_DATA_RECORD]);
                    }
                }
            }
            report.append(System.lineSeparator()).append(name).append(REPORT_SEPARATOR)
                    .append(salaryPerEmployee);
            salaryPerEmployee = 0;
        }

        return report.toString();
    }
}
