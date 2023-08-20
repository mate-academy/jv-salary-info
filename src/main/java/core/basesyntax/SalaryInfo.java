package core.basesyntax;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    private static final String DATE_FORMAT = "dd.MM.yyyy";
    private static final String REPORT_HEADER = "Report for period ";
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append(REPORT_HEADER).append(dateFrom)
                .append(" - ").append(dateTo).append(System.lineSeparator());

        LocalDate startWork = LocalDate.parse(dateFrom, DATE_TIME_FORMATTER);
        LocalDate finishWork = LocalDate.parse(dateTo, DATE_TIME_FORMATTER);

        for (String name : names) {
            int totalSalary = 0;
            for (String record : data) {
                String[] fields = record.split("\\s+");
                LocalDate dayWork = LocalDate.parse(fields[0], DATE_TIME_FORMATTER);

                if (fields[1].equals(name) && !dayWork.isBefore(startWork)
                        && !dayWork.isAfter(finishWork)) {
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
