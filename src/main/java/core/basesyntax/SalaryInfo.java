package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder("Report for period ")
                                        .append(dateFrom).append(" - ")
                                        .append(dateTo);
        dateFrom = dateFrom.replace('.', '/');
        dateTo = dateTo.replace('.', '/');

        try {
            Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateFrom);
            Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateTo);

            for (String name: names) {
                int totalSalary = 0;

                for (int i = 0; i < data.length; i++) {
                    String[] employeeInfo = data[i].split(" ");
                    Date workDay = new SimpleDateFormat("dd/MM/yyyy")
                            .parse(employeeInfo[0].replace('.', '/'));

                    if (name.equals(employeeInfo[1])
                            && workDay.compareTo(startDate) > 0
                            && (workDay.compareTo(endDate) == 0
                            || workDay.compareTo(endDate) < 0)) {
                        totalSalary += Integer.parseInt(employeeInfo[2])
                                * Integer.parseInt(employeeInfo[3]);
                    }
                }

                builder.append("\n").append(name).append(" - ").append(totalSalary);
            }
        } catch (ParseException e) {
            System.out.println("Exception: " + e);
        }

        return builder.toString();
    }
}
