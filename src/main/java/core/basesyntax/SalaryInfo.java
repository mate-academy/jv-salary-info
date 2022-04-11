package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    private static final SimpleDateFormat DATEFORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            Date startDate = DATEFORMAT.parse(dateFrom);
            Date endDate = DATEFORMAT.parse(dateTo);
            Date salaryDate = null;
            DateValidator checker = new DateValidator(startDate, endDate);
            stringBuilder.append("Report for period ")
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
                        salaryDate = DATEFORMAT.parse(fields[0]);
                        if (checker.isWithinRange(salaryDate) && name.equals(fields[1])) {
                            salary += Integer.parseInt(fields[2]) * Integer.parseInt(fields[3]);
                        }
                    }
                    stringBuilder.append(System.lineSeparator())
                            .append(name)
                            .append(" - ")
                            .append(salary);
                }
            }
        } catch (ParseException e) {
            System.out.println("Failed to parse date");
        }
        return stringBuilder.toString();
    }
}

