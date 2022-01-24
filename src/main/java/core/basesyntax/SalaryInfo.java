package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] splitRowOfData;
        StringBuilder stringBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo
                + System.lineSeparator());
        String dataName;
        LocalDate dataDate;
        LocalDate dateF = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate dateT = LocalDate.parse(dateTo, FORMATTER);
        int dataHours;
        int dataIncomePerHour;
        int salaryForPeriod = 0;
        for (String name : names) {
            stringBuilder.append(name);
            stringBuilder.append(" - ");
            for (String dataRow : data) {
                splitRowOfData = dataRow.split(" ");
                dataDate = LocalDate.parse(splitRowOfData[0], FORMATTER);
                dataName = splitRowOfData[1];
                dataHours = Integer.parseInt(splitRowOfData[2]);
                dataIncomePerHour = Integer.parseInt(splitRowOfData[3]);
                if (name.equals(dataName)
                        && (dataDate.isAfter(dateF)
                        && dataDate.isBefore(dateT)
                        || (dataDate.isEqual(dateF)
                        || dataDate.isEqual(dateT)))
                ) {
                    salaryForPeriod += dataHours * dataIncomePerHour;
                }
            }
            stringBuilder.append(salaryForPeriod);
            stringBuilder.append(System.lineSeparator());
            salaryForPeriod = 0;
        }
        return stringBuilder.toString().trim();
    }
}
