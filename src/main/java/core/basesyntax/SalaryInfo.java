package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter dateTimeFormatter
            = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate;
        LocalDate endDate;
        startDate = LocalDate.parse(dateFrom, dateTimeFormatter);
        endDate = LocalDate.parse(dateTo, dateTimeFormatter);

        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ")
                .append(dateFrom).append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name : names) {
            int totalEarned = 0;

            for (String input : data) {
                String[] parts = input.split(" ");
                String inputDate = parts[0];
                String inputName = parts[1];
                int hoursWorkedOfDay = Integer.parseInt(parts[2]);
                int rateOfDay = Integer.parseInt(parts[3]);

                LocalDate localDate = LocalDate.parse(inputDate, dateTimeFormatter);

                if (inputName.equals(name) && localDate.isAfter(startDate.minusDays(1))
                        && localDate.isBefore(endDate.plusDays(1))) {
                    totalEarned += hoursWorkedOfDay * rateOfDay;
                }
            }
            builder.append(name).append(" - ").append(totalEarned).append(System.lineSeparator());
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        SalaryInfo salaryInfo = new SalaryInfo();
        String[] names = {"Іван", "Андрій", "Катя", "Андрій", "Іван"};
        String[] data = {
                "26.04.2019 Іван 4 50",
                "05.04.2019 Андрій 3 200",
                "10.04.2019 Іван 7 100",
                "22.04.2019 Катерина 9 100",
                "25.06.2019 Іван 11 50",
                "26.04.2019 Андрій 3 150",
                "13.02.2019 Іван 7 100",
                "26.04.2019 Катя 9 100"
        };
        String dateFrom = "01.04.2019";
        String dateTom = "30.04.2019";
        String salary = salaryInfo.getSalaryInfo(names, data, dateFrom, dateTom);
        System.out.println(salary);
    }
}
