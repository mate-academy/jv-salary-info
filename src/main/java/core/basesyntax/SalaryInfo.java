package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter
            DATETIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final String headMessage = "Report for period ";
        StringBuilder totalSalaryInfo = new StringBuilder();
        totalSalaryInfo.append(headMessage + dateFrom + " - " + dateTo);
        for (String name : names) {
            int usersSalary = 0;
            for (int i = 0; i < data.length; i++) {
                String[] dataParse = data[i].split("\\s");
                String workingDayDate = dataParse[0];
                String workerName = dataParse[1];
                String workingHourPerDay = dataParse[2];
                String workingSalaryPerHour = dataParse[3];
                LocalDate dataParseDate =
                        LocalDate.parse(workingDayDate, DATETIME_FORMATTER);
                LocalDate dateFromDat =
                        LocalDate.parse(dateFrom, DATETIME_FORMATTER);
                LocalDate dateToDate =
                        LocalDate.parse(dateTo, DATETIME_FORMATTER);
                if ((dataParseDate.isAfter(dateFromDat)
                        || dataParseDate.equals(dateFromDat))
                        && (dataParseDate.isBefore(dateToDate)
                        || dataParseDate.equals(dateToDate))
                        && name.equals(workerName)) {
                    usersSalary += Integer.valueOf(workingHourPerDay)
                            * Integer.valueOf(workingSalaryPerHour);
                }
            }
            totalSalaryInfo.append(System.lineSeparator() + name + " - " + usersSalary);
        }
        return totalSalaryInfo.toString();
    }
}
