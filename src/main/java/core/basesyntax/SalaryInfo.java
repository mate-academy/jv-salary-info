package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter TIME_FORMATTER =
                        DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static String getSalaryInfo(String[] names, String[] data,
                                       String dateFrom, String dateTo) {
        LocalDate dateFromTime = LocalDate.parse(dateFrom, TIME_FORMATTER);
        LocalDate dateToTime = LocalDate.parse(dateTo, TIME_FORMATTER);
        LocalDate currentDateSalary;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ")
                     .append(dateFrom).append(" - ")
                     .append(dateTo).append("\n");

        for (String nameEmployee : names) {
            int salary = 0;
            for (String dataEmployee : data) {
                if (dataEmployee.contains(nameEmployee)) {
                    String[] arrayDateNameHourIncome = dataEmployee.split(" ");
                    currentDateSalary = LocalDate.parse(arrayDateNameHourIncome[0],
                                                        TIME_FORMATTER);
                    if ((currentDateSalary.isAfter(dateFromTime)
                            && currentDateSalary.isBefore(dateToTime))
                            || currentDateSalary.equals(dateToTime)) {
                        salary += Integer.parseInt(arrayDateNameHourIncome[2])
                                * Integer.parseInt(arrayDateNameHourIncome[3]);
                    }
                }
            }
            stringBuilder.append(nameEmployee).append(" - ")
                         .append(salary).append("\n");
        }
        return stringBuilder.toString().trim();
    }
}
