package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME = 1;
    private static final int WORK_HOURS = 2;
    private static final int MONEY_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder result = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        String[] dataInfo;
        LocalDate dateStart = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);
        int totalSalary;
        for (String name : names) {
            totalSalary = 0;
            for (String info : data) {
                dataInfo = info.split(" ");
                if (name.equals(dataInfo[NAME])) {
                    LocalDate dateNow = LocalDate.parse(dataInfo[DATE_INDEX], formatter);
                    if (!dateNow.isBefore(dateStart) && dateNow.isBefore(dateEnd.plusDays(1))) {
                        totalSalary += Integer.parseInt(dataInfo[WORK_HOURS]) * Integer.parseInt(dataInfo[MONEY_PER_HOUR]);
                    }
                }
            }
            result.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(totalSalary);
        }
        return result.toString();
    }
}
