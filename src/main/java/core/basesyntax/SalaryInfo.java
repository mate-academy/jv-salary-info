package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_POSITION = 0;
    private static final int NAME_POSITION = 1;
    private static final int WORK_HOURS_POSITION = 2;
    private static final int INCOME_PER_HOUR_POSITION = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        LocalDate localDateFrom = LocalDate.parse(dateFrom, formatter);
        LocalDate localDateTo = LocalDate.parse(dateTo, formatter);

        builder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int totalIncome = 0;
            for (String daraRow : data) {
                LocalDate date =
                        LocalDate.parse(daraRow.split(" ")[DATE_POSITION], formatter);
                String nameFromData =
                        daraRow.split(" ")[NAME_POSITION];
                int workedHours =
                        Integer.parseInt(daraRow.split(" ")[WORK_HOURS_POSITION]);
                int incomePerHour =
                        Integer.parseInt(daraRow.split(" ")[INCOME_PER_HOUR_POSITION]);
                int totalIncomePerDay = workedHours * incomePerHour;

                if (date.isBefore(localDateTo) && date.isAfter(localDateFrom)
                        || date.isEqual(localDateTo) || date.isEqual(localDateFrom)) {
                    if (name.equals(nameFromData)) {
                        totalIncome += totalIncomePerDay;
                    }
                }
            }
            builder.append(name)
                    .append(" - ")
                    .append(totalIncome)
                    .append(System.lineSeparator());
        }

        return builder.toString().trim();
    }
}
