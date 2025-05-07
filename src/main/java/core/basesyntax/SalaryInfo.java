package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int MULTIPLE_INDEX = 2;
    private static final int SALARY_INDEX = 3;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int money = 0;
            for (String checkData : data) {
                String[] rowData = checkData.split(" ");
                String date = rowData[DATE_INDEX];
                String checkName = rowData[NAME_INDEX];
                int multiple = Integer.parseInt(rowData[MULTIPLE_INDEX]);
                int salary = Integer.parseInt(rowData[SALARY_INDEX]);
                LocalDate start = LocalDate.parse(dateFrom, DATE_FORMAT).minusDays(1);
                LocalDate process = LocalDate.parse(date, DATE_FORMAT);
                LocalDate finish = LocalDate.parse(dateTo, DATE_FORMAT).plusDays(1);
                if (name.equals(checkName)
                        && start.isBefore(process) && process.isBefore(finish)) {
                    money += salary * multiple;
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name).append(" - ").append(money);
        }
        return reportBuilder.toString();
    }
}
