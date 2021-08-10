package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate from = DateParser.parse(dateFrom);
        LocalDate to = DateParser.parse(dateTo);
        StringBuilder report = new StringBuilder("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int salary = 0;
            for (String datum : data) {
                InputStat inputStat = StatParser.parse(datum);
                if (inputStat.getName().equals(name)
                        && inputStat.getDate().isAfter(from.minusDays(1))
                        && inputStat.getDate().isBefore(to.plusDays(1))) {
                    salary += inputStat.getHours() * inputStat.getRate();
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
