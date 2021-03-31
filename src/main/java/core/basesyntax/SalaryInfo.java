package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {
        System.out.println();
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = getDateFromString(dateFrom);
        LocalDate finishDate = getDateFromString(dateTo);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String info : data) {
                if (info.contains(name)) {
                    String [] array = info.split(" ");
                    LocalDate currentDate = getDateFromString(array[0]);

                    if (currentDate.isAfter(startDate)
                            && currentDate.isBefore(finishDate.plusDays(1))) {
                        salary += Integer.valueOf(array[2]) * Integer.valueOf(array[3]);
                    }
                }
            }
            stringBuilder.append("\n").append(name).append(" - ").append(salary);
        }
        System.out.println(stringBuilder.toString().trim());
        return stringBuilder.toString().trim();
    }

    private static LocalDate getDateFromString(String date) {
        return LocalDate.parse(date, FORMATTER);
    }
}
