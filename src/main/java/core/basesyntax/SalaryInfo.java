package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Date dateBegin;
        try {
            dateBegin = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
        } catch (Exception e) {
            System.out.println("Wrong dateFrom! " + e);
            return "";
        }

        Date dateEnd;
        try {
            dateEnd = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
        } catch (Exception e) {
            System.out.println("Wrong dateTo! " + e);
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Report for period ");
        stringBuilder.append(dateFrom);
        stringBuilder.append(" - ");
        stringBuilder.append(dateTo);
        for (String name : names) {
            int amount = 0;
            for (String employeeData : data) {
                if (!employeeData.contains(name)) {
                    continue;
                }
                Date dataDate;
                try {
                    dataDate = new SimpleDateFormat("dd.MM.yyyy").parse(employeeData);
                } catch (Exception e) {
                    System.out.println("Wrong date! " + e);
                    continue;
                }
                if (!dataDate.before(dateBegin) && !dataDate.after(dateEnd)) {
                    String[] dataArray = employeeData.split(" ");
                    amount += Integer.parseInt(dataArray[2]) * Integer.parseInt(dataArray[3]);
                }
            }
            stringBuilder.append(System.lineSeparator());
            stringBuilder.append(name);
            stringBuilder.append(" - ");
            stringBuilder.append(amount);
        }
        return stringBuilder.toString();
    }
}
