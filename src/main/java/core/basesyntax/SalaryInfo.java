package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] to = dateTo.split("\\.");
        String[] from = dateFrom.split("\\.");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        LocalDate start = LocalDate.of(Integer.parseInt(from[2]), Integer.parseInt(from[1]),
                Integer.parseInt(from[0]));
        LocalDate stop = LocalDate.of(Integer.parseInt(to[2]), Integer.parseInt(to[1]),
                Integer.parseInt(to[0]));

        for (String name : names) {
            int salary = 0;
            for (String day : data) {
                String[] info = day.split(" ");
                String[] date = info[0].split("\\.");
                LocalDate workDay = LocalDate.of(Integer.parseInt(date[2]),
                        Integer.parseInt(date[1]), Integer.parseInt(date[0]));
                if (info[1].equals(name) & (workDay.isEqual(start) | workDay.isEqual(stop)
                        | (workDay.isAfter(start) & workDay.isBefore(stop)))) {
                    salary += Integer.parseInt(info[2]) * Integer.parseInt(info[3]);
                }
            }
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return stringBuilder.toString();
    }
}
