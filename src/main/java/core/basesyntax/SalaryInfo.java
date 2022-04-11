package core.basesyntax;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class SalaryInfo {
    private static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final int DATE_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int HOURS_INDEX = 2;
    private static final int INCOME_PER_HOUR_INDEX = 3;

    public boolean isWithinRange(LocalDate salaryDate) {
        boolean result = false;
        LocalDate startDate = null;
        LocalDate endDate = null;
        if ((salaryDate.equals(startDate) || salaryDate.equals(endDate))
                || (salaryDate.isAfter(startDate) && salaryDate.isBefore(endDate))) {
            result = true;
        }
        return result;
    }

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder reportBuilder = new StringBuilder();
        try {
            LocalDate startDate = LocalDate.parse(dateFrom, DATEFORMAT);
            LocalDate endDate = LocalDate.parse(dateTo, DATEFORMAT);
            LocalDate salaryDate = null;
            reportBuilder.append("Report for period ")
                         .append(DATEFORMAT.format(startDate))
                         .append(" - ")
                         .append(DATEFORMAT.format(endDate));
            String[] fields = new String[4];
            int salary;
            if (dateFrom != null && dateTo != null) {
                for (String name : names) {
                    salary = 0;
                    for (String dataStr : data) {
                        fields = dataStr.split(" ");
                        salaryDate = LocalDate.parse(fields[DATE_INDEX], DATEFORMAT);
                        if (isWithinRange(salaryDate) && name.equals(fields[NAME_INDEX])) {
                            salary += Integer.parseInt(fields[HOURS_INDEX]) * Integer.parseInt(fields[INCOME_PER_HOUR_INDEX]);
                        }
                    }
                    reportBuilder.append(System.lineSeparator())
                            .append(name)
                            .append(" - ")
                            .append(salary);
                }
            }
        } catch (ParseException e) {
            System.out.println("Failed to parse date");
        }
        return reportBuilder.toString();
    }
}

