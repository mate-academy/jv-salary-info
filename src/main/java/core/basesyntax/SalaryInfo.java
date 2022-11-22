package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String nameAndSalarySum = "";
        for (int i = 0; i < names.length; i++) {
            int salarySum = 0;
            for (String s : data) {
                String[] splitData = s.split(" ");
                if (dateRange(dateFrom, splitData[0], dateTo) == true
                        && splitData[1].equals(names[i])) {
                    salarySum += getMonthSalary(splitData[2], splitData[3]);
                }
            }
            if (i == names.length - 1) {
                nameAndSalarySum += names[i] + " - " + salarySum;
            } else {
                nameAndSalarySum += names[i] + " - " + salarySum + "\n";
            }
        }
        return String.format("Report for period %s - %s\n%s", dateFrom, dateTo, nameAndSalarySum);
    }

    private Date getDateFormat(String stringDate) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        try {
            return format.parse(stringDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean dateRange(String dateFrom, String correctDate, String dateTo) {
        return !getDateFormat(correctDate).before(getDateFormat(dateFrom))
                && !getDateFormat(correctDate).after(getDateFormat(dateTo));
    }

    private int getMonthSalary(String splitData2, String splitData3) {
        return Integer.parseInt(splitData2) * Integer.parseInt(splitData3);
    }
}
