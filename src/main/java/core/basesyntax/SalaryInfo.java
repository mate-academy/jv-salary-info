package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            int salary = 0;
            report.append(System.lineSeparator()).append(name).append(" - ");
            for (String info : data) {
                String[] Draw = info.split(" ");
                if ((formatter(Draw[0]).isAfter(formatter(dateFrom))
                        || formatter(Draw[0]).equals(formatter(dateFrom)))
                        && (formatter(Draw[0]).equals(formatter(dateTo))
                        || formatter(Draw[0]).isBefore(formatter(dateTo)))
                        && Draw[1].equals(name)) {
                    salary += getSalaryFromInfo(Draw);
                }
            }
            report.append(salary);

        }
        return report.toString();
    }

    private LocalDate formatter(String info) {
        return LocalDate.parse(info, formatter);
    }

    private int getSalaryFromInfo(String[] Draw) {
        int hours = Integer.parseInt(Draw[2]);
        int value = Integer.parseInt(Draw[3]);
        return hours * value;
    }
}
