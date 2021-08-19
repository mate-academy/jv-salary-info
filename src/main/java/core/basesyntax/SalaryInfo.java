package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE = 0;
    private static final int NAME = 1;
    private static final int MULTIPLE = 2;
    private static final int SALARY = 3;
    private static final DateTimeFormatter DATE_COUNT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder printer = new StringBuilder();
        printer.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int money = 0;
            for (String checkData : data) {
                String[] dates = checkData.split(" ");
                String date = dates[DATE];
                String checkName = dates[NAME];
                int multiple = Integer.parseInt(dates[MULTIPLE]);
                int salary = Integer.parseInt(dates[SALARY]);
                LocalDate start = LocalDate.parse(dateFrom, DATE_COUNT).minusDays(1);
                LocalDate process = LocalDate.parse(date, DATE_COUNT);
                LocalDate finish = LocalDate.parse(dateTo, DATE_COUNT).plusDays(1);
                if (name.equals(checkName)
                        && start.isBefore(process) & process.isBefore(finish)) {
                    money += salary * multiple;
                }
            }
            printer.append(System.lineSeparator()).append(name).append(" - ").append(money);
        }
        return printer.toString();
    }
}
