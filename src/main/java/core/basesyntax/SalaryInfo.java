package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_FROM_ARRAY = 0;
    private static final int NAME_OF_EMPLOYEE = 1;
    private static final int WORKING_HOURS = 2;
    private static final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocalDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        LocalDate dateToLocalDate = LocalDate.parse(dateTo, dateTimeFormatter);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            builder.append(System.lineSeparator());
            int salary = 0;
            for (String elementFromData : data) {
                String[] arrayFromData = elementFromData.split(" ");
                LocalDate dateFromArray = LocalDate.parse(arrayFromData[DATE_FROM_ARRAY],
                        dateTimeFormatter);
                String nameOfEmployee = arrayFromData[NAME_OF_EMPLOYEE];
                int workingHours = Integer.parseInt(arrayFromData[WORKING_HOURS]);
                int incomePerHour = Integer.parseInt(arrayFromData[INCOME_PER_HOUR]);
                if (name.equals(nameOfEmployee) && ((dateFromArray.isAfter(dateFromLocalDate)
                        || dateFromArray.equals(dateToLocalDate))
                        && (dateFromArray.isBefore(dateToLocalDate)
                        || dateFromArray.equals(dateToLocalDate)))) {
                    salary += workingHours * incomePerHour;
                }
            }
            builder.append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
