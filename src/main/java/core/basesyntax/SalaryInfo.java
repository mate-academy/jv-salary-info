package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private String patternFormat = "dd.MM.yyyy";
    private Locale localeUa = new Locale("ua", "UA");
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFormat)
            .withLocale(localeUa);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        LocalDate dateBegin = LocalDate.parse(dateFrom, formatter);
        LocalDate dateEnd = LocalDate.parse(dateTo, formatter);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int amount = 0;
            for (String employeeData : data) {
                String[] dataArray = employeeData.split(" ");
                LocalDate dataDate = LocalDate.parse(dataArray[0], formatter);
                if (employeeData.contains(name) && !dataDate.isBefore(dateBegin)
                        && !dataDate.isAfter(dateEnd)) {
                    amount += Integer.parseInt(dataArray[2]) * Integer.parseInt(dataArray[3]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(amount);
        }
        return report.toString();
    }
}
