package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromNow = LocalDate.parse(dateFrom, df);
        LocalDate dateToNow = LocalDate.parse(dateTo, df);
        StringBuilder salaryBuilder = new StringBuilder();
        salaryBuilder.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            int count = 0;
            for (String dataEntry : data) {
                String[] separator = dataEntry.split(" ");
                int hourlyRateIndex = Integer.parseInt(separator[3]);
                int houseWorkedIndex = Integer.parseInt(separator[2]);
                LocalDate dateWork = LocalDate.parse(separator[0], df);
                String workerName = separator[1];
                if (name.equals(workerName)
                        && (dateWork.isAfter(dateFromNow) || dateWork.isEqual(dateFromNow))
                        && (dateWork.isBefore(dateToNow) || dateWork.isEqual(dateToNow))) {
                    count += hourlyRateIndex * houseWorkedIndex;
                }
            }
            salaryBuilder.append(System.lineSeparator())
                    .append(name)
                    .append(" - ")
                    .append(count);
        }
        return salaryBuilder.toString();
    }
}
