package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDay = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate finalDay = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String record : data) {
                String[] workingDayInfo = record.split(" ");
                LocalDate workingDay = LocalDate.parse(workingDayInfo[0], FORMATTER);
                String employeeName = workingDayInfo[1];
                int workingHours = Integer.parseInt(workingDayInfo[2]);
                int payForHour = Integer.parseInt(workingDayInfo[3]);
                if (name != null && name.equals(employeeName)) {
                    if ((workingDay.equals(startDay) || workingDay.isAfter(startDay))
                            && (workingDay.equals(finalDay)
                            || workingDay.isBefore(finalDay))) {
                        int dayIncome = workingHours * payForHour;
                        salary += dayIncome;
                    }
                }
            }
            builder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return builder.toString();
    }
}
