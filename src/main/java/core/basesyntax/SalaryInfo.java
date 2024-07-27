package core.basesyntax;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int salary = 0;
            for (String line : data) {
                String[] parts = line.split(" ");
                if (parts[1].equals(name)) {
                    LocalDate workDate = LocalDate.parse(parts[0], formatter);
                    if (workDate.isAfter(startDate.minusDays(1)) && workDate.isBefore(endDate.plusDays(1))) {
                        int hours = Integer.parseInt(parts[2]);
                        int rate = Integer.parseInt(parts[3]);
                        salary += hours * rate;
                    }
                }
            }
            report.append("\n").append(name).append(" - ").append(Integer.toString(salary));
        }

        return report.toString();
    }
}