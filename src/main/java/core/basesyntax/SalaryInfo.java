package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMAT_DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String GAP_SPLITTER = " ";
    private static final int INDEX_OF_DATA = 0;
    private static final int INDEX_OF_NAME = 1;
    private static final int INDEX_OF_HOURS = 2;
    private static final int INDEX_OF_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, FORMAT_DATE);
        LocalDate localDateTo = LocalDate.parse(dateTo, FORMAT_DATE);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            builder.append(LINE_SEPARATOR).append(name).append(" - ");
            int salaryPerPeriodForEmployee
                    = getSalaryPerPeriodForEmployee(data, name,localDateFrom,localDateTo);
            builder.append(salaryPerPeriodForEmployee);
        }
        return builder.toString();
    }

    private boolean isDateInPeriod(String dateInRecord, LocalDate dateFrom, LocalDate dateTo) {
        LocalDate localDate = LocalDate.parse(dateInRecord, FORMAT_DATE);
        return ((localDate.isAfter(dateFrom) || localDate.isEqual(dateFrom))
                && (localDate.isBefore(dateTo) || localDate.isEqual(dateTo)));
    }

    private int getSalaryPerPeriodForEmployee(String[] data, String employeeName,
                                              LocalDate localDateFrom, LocalDate localDateTo) {
        int salaryPerPeriodForEmployee = 0;
        for (String record: data) {
            String[] splitRecordForEmployee = record.split(GAP_SPLITTER);
            String dateInRecord = splitRecordForEmployee[INDEX_OF_DATA];
            String nameOfEmployeeInRecord = splitRecordForEmployee[INDEX_OF_NAME];
            if (employeeName.equals(nameOfEmployeeInRecord)
                    && isDateInPeriod(dateInRecord, localDateFrom, localDateTo)) {
                salaryPerPeriodForEmployee
                        += Integer.parseInt(splitRecordForEmployee[INDEX_OF_HOURS])
                        * Integer.parseInt(splitRecordForEmployee[INDEX_OF_PER_HOUR]);
            }
        }
        return salaryPerPeriodForEmployee;
    }
}
