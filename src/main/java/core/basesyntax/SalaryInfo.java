package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder sb = new StringBuilder("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        LocalDate locFrom = getLocalDate(dateFrom);
        LocalDate locTo = getLocalDate(dateTo);

        for (String name : names) {
            sb.append(System.lineSeparator());
            int total = 0;

            for (String d : data) {
                String currentDate = d.split(" ")[0];
                LocalDate loc = getLocalDate(currentDate);
                String currentName = d.split(" ")[1];
                int hours = Integer.parseInt(d.split(" ")[2]);
                int perHour = Integer.parseInt(d.split(" ")[3]);

                if (currentName.equals(name) && !loc.isBefore(locFrom) && !loc.isAfter(locTo)) {
                    total += hours * perHour;
                }
            }

            sb.append(name).append(" - ").append(total);
        }
        return sb.toString();
    }

    public LocalDate getLocalDate(String date) {
        String[] d = date.split("\\.");
        int year = Integer.parseInt(d[2]);
        int month = Integer.parseInt(d[1]);
        int day = Integer.parseInt(d[0]);

        return LocalDate.of(year, month, day);
    }
}
