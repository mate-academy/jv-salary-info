package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Date dateBegin;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            dateBegin = dateFormat.parse(dateFrom);
        } catch (Exception e) {
            System.out.println("Wrong dateFrom! " + e);
            return "";
        }
        Date dateEnd;
        try {
            dateEnd = dateFormat.parse(dateTo);
        } catch (Exception e) {
            System.out.println("Wrong dateTo! " + e);
            return "";
        }
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int amount = 0;
            for (String employeeData : data) {
                if (!employeeData.contains(name)) {
                    continue;
                }
                Date dataDate;
                try {
                    dataDate = dateFormat.parse(employeeData);
                } catch (Exception e) {
                    System.out.println("Wrong date! " + e);
                    continue;
                }
                if (!dataDate.before(dateBegin) && !dataDate.after(dateEnd)) {
                    String[] dataArray = employeeData.split(" ");
                    amount += Integer.parseInt(dataArray[2]) * Integer.parseInt(dataArray[3]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(amount);
        }
        return report.toString();
    }
}
