package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final StringBuilder result = new StringBuilder();
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOUR_INDEX = 2;
    private static final int INCOME_PER_DAY_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int moneyEarned = 0;
        result.delete(0,result.length());
        LocalDate outputdateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate outputdateTo = LocalDate.parse(dateTo, formatter);
        result.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String nameCurrentEmployee : names) {
            moneyEarned = 0;
            for (String dateInfoCurrent : data) {
                String[] currentDayInfo = dateInfoCurrent.split(" ");
                LocalDate dateCurrentDate = LocalDate.parse(currentDayInfo[DATE_INDEX], formatter);
                if (dateCurrentDate.isAfter(outputdateFrom)
                        && (dateCurrentDate.isBefore(outputdateTo)
                        || dateCurrentDate.isEqual(outputdateTo))
                        && nameCurrentEmployee.equals(currentDayInfo[NAME_INDEX])) {
                    moneyEarned = moneyEarned + Integer.parseInt(currentDayInfo[WORKING_HOUR_INDEX])
                           * Integer.parseInt(currentDayInfo[INCOME_PER_DAY_INDEX]);
                }
            }
            result.append(System.lineSeparator()).append(nameCurrentEmployee)
                    .append(" - ").append(moneyEarned);
        }
        return result.toString();
    }
}
