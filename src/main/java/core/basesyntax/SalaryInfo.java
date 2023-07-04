package core.basesyntax;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String REPORT_HEADER = "Report for period ";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append(REPORT_HEADER).append(dateFrom).append(" - ").append(dateTo).append("\n");
        LocalDate fromDate = LocalDate.parse(dateFrom, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate toDate = LocalDate.parse(dateTo, DateTimeFormatter.ofPattern(DATE_FORMAT));

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] fields = record.split(" ");
                LocalDate recordDate = LocalDate.parse(fields[0],
                        DateTimeFormatter.ofPattern(DATE_FORMAT));
                if (fields[1].equals(name) && !recordDate.isBefore(fromDate)
                        && !recordDate.isAfter(toDate)) {
                    int hours = Integer.parseInt(fields[2]);
                    int rate = Integer.parseInt(fields[3]);
                    int salary = hours * rate;
                    totalSalary += salary;
                }
            }
            report.append(name).append(" - ").append(formatSalary(totalSalary))
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }

    private String formatSalary(int salary) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
        DecimalFormat decimalFormat = (DecimalFormat) formatter;
        decimalFormat.applyPattern("0");
        return decimalFormat.format(salary);
    }
}
