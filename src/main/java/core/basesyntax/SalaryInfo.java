package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int WORKING_HOURS_INDEX = 2;
    private static final int SALARY_PER_HOUR_INDEX = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        LocalDate localDateFrom = LocalDate.parse(dateFrom, DTF);
        LocalDate localDateTo = LocalDate.parse(dateTo, DTF);
        for (int i = 0; i < names.length; i++) {
            int salaryCalculate = 0;
            String name = names[i];
            for (int j = 0; j < data.length; j++) {
                String[] totalInfo = data[j].split(" ");
                LocalDate date = LocalDate.parse(totalInfo[DATE_INDEX],DTF);
                String nameFromTotalInfo = totalInfo[NAME_INDEX];
                int workHours = Integer.parseInt(totalInfo[WORKING_HOURS_INDEX]);
                int salaryPerHour = Integer.parseInt(totalInfo[SALARY_PER_HOUR_INDEX]);
                if (name.equals(nameFromTotalInfo)) {
                    if (!date.isAfter(localDateTo) && !date.isBefore(localDateFrom)) {
                        salaryCalculate += workHours * salaryPerHour;
                    }
                }
            }
            builder.append(System.lineSeparator())
                    .append(name).append(" - ")
                    .append(salaryCalculate);
        }
        return builder.toString();
    }
}
