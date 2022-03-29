package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate parseDateFrom = LocalDate.parse(dateFrom, FORMATTER);
        LocalDate parseDateTo = LocalDate.parse(dateTo, FORMATTER);
        StringBuilder report = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);
        String[] getInfoWithDate;

        for (String name : names) {
            int salary = 0;
            for (String dat : data) {
                getInfoWithDate = dat.split(" ");
                LocalDate currentDate = LocalDate.parse(getInfoWithDate[0], FORMATTER);
                if (parseDateFrom.compareTo(currentDate) <= 0
                        && parseDateTo.compareTo(currentDate) >= 0
                        && getInfoWithDate[1].equals(name)) {
                    salary += (Integer.parseInt(getInfoWithDate[3])
                            * Integer.parseInt(getInfoWithDate[2]));
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return report.toString();
    }
}
