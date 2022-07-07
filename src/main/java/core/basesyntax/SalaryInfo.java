package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private StringBuilder totalSalaryInfo = new StringBuilder();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final String dateFormat = "dd.MM.yyyy";
        final String headMessage = "Report for period ";
        totalSalaryInfo.delete(0, totalSalaryInfo.length());
        totalSalaryInfo.append(headMessage + dateFrom + " - " + dateTo);
        for (String name : names) {
            int usersSalary = 0;
            if (name == null || name.equals("")) {
                continue;
            }
            for (int i = 0; i < data.length; i++) {
                String[] dataParse = data[i].split("\\s");
                LocalDate dataParseDate =
                        LocalDate.parse(dataParse[0], DateTimeFormatter.ofPattern(dateFormat));
                LocalDate dateFromDat =
                        LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern(dateFormat));
                LocalDate dateToDate =
                        LocalDate.parse(dateTo, DateTimeFormatter.ofPattern(dateFormat));
                if ((dataParseDate.isAfter(dateFromDat)
                        || dataParseDate.equals(dateFromDat))
                        && (dataParseDate.isBefore(dateToDate)
                        || dataParseDate.equals(dateToDate))
                        && name.equals(dataParse[1])) {
                    usersSalary += Integer.valueOf(dataParse[2])
                            * Integer.valueOf(dataParse[3]);
                }
            }
            totalSalaryInfo.append(System.lineSeparator() + name + " - " + usersSalary);
        }
        return totalSalaryInfo.toString();
    }
}
