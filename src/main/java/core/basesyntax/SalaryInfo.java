package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = 
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE = 0;
    private static final int NAME_OF_EMPLOYEE = 1;
    private static final int NUMBER_OF_DAYS = 1;
    private static final int WORKING_HOURS = 2;
    private static final int SALARY_PER_HOUR = 3;
    
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate finishDate = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);
        StringBuilder reportBuilder = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);
        for (String name : names) {
            int amontOfMoney = 0;
            for (String datum : data) {
                String[] split = datum.split(" ");
                LocalDate separateDate = LocalDate.parse(split[DATE], DATE_TIME_FORMATTER);
                if (name.equals(split[NAME_OF_EMPLOYEE])
                        && (separateDate.isAfter(startDate) || separateDate.equals(startDate))
                        && (separateDate.isBefore(finishDate.plusDays(NUMBER_OF_DAYS))
                        || separateDate.equals(finishDate))) {
                    amontOfMoney += Integer.parseInt(split[WORKING_HOURS])
                            * Integer.parseInt(split[SALARY_PER_HOUR]);
                }
            }
            reportBuilder.append(System.lineSeparator()).append(name)
                    .append(" - ").append(amontOfMoney);
        }
        return reportBuilder.toString();
    }
}
