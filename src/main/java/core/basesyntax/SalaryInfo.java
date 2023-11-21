package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_WORKED_INDEX = 2;
    private static final int HOURLY_RATE = 3;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate localDateFrom = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, dateFormatter);
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append(System.lineSeparator());

        for (String name : names) {
            int salary = 0;

            for (String fullData : data) {
                String[] infoData = fullData.split(" ");
                LocalDate dateToCheck = LocalDate.parse(infoData[DATE_INDEX], dateFormatter);
                String nameOfEmploy = infoData[NAME_INDEX];
                int hours = Integer.parseInt(infoData[HOURS_WORKED_INDEX]);
                int salaryForHour = Integer.parseInt(infoData[HOURLY_RATE]);
                if (nameOfEmploy.equals(name) && !dateToCheck.isBefore(localDateFrom)
                        && !dateToCheck.isAfter(localDateTo)) {
                    salary += hours * salaryForHour;
                }
            }

            salaryInfo.append(name).append(" - ").append(salary)
                    .append(System.lineSeparator());
        }
        salaryInfo.delete(salaryInfo.length() - System.lineSeparator().length(),
                salaryInfo.length());
        return salaryInfo.toString();
    }
}
