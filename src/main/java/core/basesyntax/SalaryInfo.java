package core.basesyntax;

import java.util.Arrays;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Calendar calenderDateFrom = new Calendar(dateFrom);
        Calendar calenderDateTo = new Calendar(dateTo);
        int[] salary = new int[names.length];
        Arrays.fill(salary, 0);
        for (String strData: data) {
            if (Calendar.isDataBetweenTwoDates(new Calendar(strData),
                    calenderDateFrom, calenderDateTo)) {
                for (int i = 0; i < names.length; i++) {
                    if (names[i].equals(strData.substring(strData.indexOf(' ') + 1,
                            strData.indexOf(' ') + 1 + names[i].length()))) {
                        int workHours = Integer.parseInt(strData.substring(strData.indexOf(' ') + 2
                                + names[i].length(), strData.lastIndexOf(' ')));
                        int salaryPerHour = Integer.parseInt(strData.substring(strData
                                .lastIndexOf(' ') + 1, strData.length()));
                        salary[i] = workHours * salaryPerHour + salary[i];
                    }
                }
            }
        }
        StringBuilder allInfo = new StringBuilder();
        allInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (int i = 0; i < names.length; i++) {
            allInfo.append("\r\n").append(names[i]).append(" - ").append(salary[i]);
        }
        return allInfo.toString();
    }
}
