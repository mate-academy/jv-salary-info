package core.basesyntax;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class SalaryInfo {

    private static final DateTimeFormatter DATEFORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");


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
                        salaryDate = LocalDate.parse(fields[0], DATEFORMAT);
                        if (isWithinRange(salaryDate) && name.equals(fields[1])) {
                            salary += Integer.parseInt(fields[2]) * Integer.parseInt(fields[3]);
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

