package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter
            DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int WORKING_DAY_DATE = 0;
    private static final int WORKER_NAME = 1;
    private static final int WORKING_HOUR_PER_DAY = 2;
    private static final int WORKING_SALARY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final String headMessage = "Report for period ";
        StringBuilder totalSalaryInfo = new StringBuilder();
        totalSalaryInfo.append(headMessage + dateFrom + " - " + dateTo);
        for (String name : names) {
            int usersSalary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] dataParse = data[i].split("\\s");

                LocalDate dataParseDate =
                        LocalDate.parse(dataParse[WORKING_DAY_DATE], DATETIME_FORMATTER);
                LocalDate dateFromDat =
                        LocalDate.parse(dateFrom, DATETIME_FORMATTER);
                LocalDate dateToDate =
                        LocalDate.parse(dateTo, DATETIME_FORMATTER);
                if ((dataParseDate.isAfter(dateFromDat)
                        || dataParseDate.equals(dateFromDat))
                        && (dataParseDate.isBefore(dateToDate)
                        || dataParseDate.equals(dateToDate))
                        && name.equals(dataParse[WORKER_NAME])) {
                    usersSalary += Integer.valueOf(dataParse[WORKING_HOUR_PER_DAY])
                            * Integer.valueOf(dataParse[WORKING_SALARY_PER_HOUR]);
                }
            }
            totalSalaryInfo.append(System.lineSeparator() + name + " - " + usersSalary);
        }
        return totalSalaryInfo.toString();
    }
}
