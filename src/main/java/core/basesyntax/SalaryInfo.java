package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = new SimpleDateFormat("dd.MM.yyy").parse(dateFrom);
            endDate = new SimpleDateFormat("dd.MM.yyy").parse(dateTo);
        } catch (ParseException e) {
            System.out.println("ParseException");
        }
        for (String name : names) {
            int employeeSalary = 0;
            for (String datum : data) {
                String[] employeeData = datum.split(" ");
                Date currentDate = null;
                try {
                    currentDate = new SimpleDateFormat("dd.MM.yyy").parse(employeeData[0]);
                } catch (ParseException e) {
                    System.out.println("ParseException");
                }
                if ((currentDate.equals(startDate) || currentDate.equals(endDate)
                            || currentDate.after(startDate) && currentDate.before(endDate))
                            && employeeData[1].contains(name)) {
                    employeeSalary += Integer.parseInt(employeeData[2])
                                * Integer.parseInt(employeeData[3]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(employeeSalary);
        }
        return report.toString();
    }
}

