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
        StringBuilder builder = new StringBuilder("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        LocalDate dateFromFormatted = LocalDate.parse(dateFrom, DTF);
        LocalDate dateToFormatted = LocalDate.parse(dateTo, DTF);

        for (int i = 0; i < names.length; i++) {
            int calculatedSalary = 0;
            String name = names[i];
            for (int j = 0; j < data.length; j++) {
                String[] fullInfo = data[j].split(" ");
                LocalDate date = LocalDate.parse(fullInfo[DATE_INDEX], DTF);
                String nameInfo = fullInfo[NAME_INDEX];
                int workingHours = Integer.parseInt(fullInfo[WORKING_HOURS_INDEX]);
                int salaryPerHour = Integer.parseInt(fullInfo[SALARY_PER_HOUR_INDEX]);
                if (name.equals(nameInfo)) {
                    if (!date.isAfter(dateToFormatted) && !date.isBefore(dateFromFormatted)) {
                        calculatedSalary += (workingHours * salaryPerHour);
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ")
                    .append(calculatedSalary);
        }
        return builder.toString();
    }
}
