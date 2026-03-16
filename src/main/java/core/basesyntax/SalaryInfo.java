package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data,
                                String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, formatter);
        LocalDate to = LocalDate.parse(dateTo, formatter);

        Map<String, Integer> salary = new HashMap<>();
        for (String name : names) {
            salary.put(name, 0);
        }

        for (String record : data) {
            String[] partsOfData = record.split(" ");
            LocalDate date = LocalDate.parse(partsOfData[0], formatter);
            String name = partsOfData[1];
            int hours = Integer.parseInt(partsOfData[2]);
            int rate = Integer.parseInt(partsOfData[3]);

            if (!salary.containsKey(name)) {
                continue;
            }
            if ((date.isEqual(from) || date.isAfter(from))
                    && (date.isEqual(to) || date.isBefore(to))) {
                int earn = hours * rate;
                salary.put(name, salary.get(name) + earn);
            }
        }
        String ls = System.lineSeparator();

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo).append(ls);
        for (int i = 0; i < names.length; i++) {
            report.append(names[i]).append(" - ").append(salary.get(names[i]));
            if (i < names.length - 1) {
                report.append(ls);
            }
        }
        return report.toString();
    }
}
