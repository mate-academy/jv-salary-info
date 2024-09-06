package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        StringBuilder staffSalaryInformation = new StringBuilder("Report for period "
                                             + dateFrom + " - " + dateTo);
        for (String name : names) {
            int totalSalary = 0;
            staffSalaryInformation.append("\r\n");
            for (String dataItem : data) {
                String[] items = dataItem.split(" ");
                if (name.equals(items[1])) {
                    try {
                        Date workDate = sdf.parse(items[0]);
                        Date dateToBorder = sdf.parse(dateTo);
                        Date dateFromBorder = sdf.parse(dateFrom);

                        if ((workDate.before(dateToBorder) || workDate.equals(dateToBorder))
                                && (workDate.after(dateFromBorder)
                                || workDate.equals(dateFromBorder))) {
                            totalSalary += salaryCounter(items);
                        }
                    } catch (ParseException e) {
                        System.out.println("Something went wrong with date parsing");
                    }
                }
            }
            staffSalaryInformation.append(name).append(" - ").append(totalSalary);
        }

        return staffSalaryInformation.toString();
    }

    public int salaryCounter(String[] items) {
        return Integer.parseInt(items[2]) * Integer.parseInt(items[3]);
    }
}
