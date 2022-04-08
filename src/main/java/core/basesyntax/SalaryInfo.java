package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date startDate = dateFormat.parse(dateFrom);
            Date endDate = dateFormat.parse(dateTo);
            Date salaryDate = null;
            DateValidator checker = new DateValidator(startDate, endDate);
            stringBuilder.append("Report for period ")
                         .append(dateFormat.format(startDate))
                         .append(" - ")
                         .append(dateFormat.format(endDate));
            String[] fields = new String[4];
            int salary;
            if (dateFrom != null && dateTo != null) {
                for (String name : names) {
                    salary = 0;
                    for (String dataStr : data) {
                        fields = dataStr.split(" ");
                        salaryDate = dateFormat.parse(fields[0]);
                        if (checker.isWithinRange(salaryDate) && name.equals(fields[1])) {
                            salary += Integer.parseInt(fields[2]) * Integer.parseInt(fields[3]);
                        }
                    }
                    stringBuilder.append("\n")
                            .append(name)
                            .append(" - ")
                            .append(salary);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}

