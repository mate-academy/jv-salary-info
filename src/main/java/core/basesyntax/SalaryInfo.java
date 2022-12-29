package core.basesyntax;

import java.time.LocalDate;
import java.util.Arrays;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate dateFromLocal = LocalDate.of(Integer.parseInt(dateFrom.substring(dateFrom
                        .lastIndexOf('.') + 1,
                dateFrom.lastIndexOf('.') + 5)),
                Integer.parseInt(dateFrom.substring(dateFrom.indexOf('.') + 1,
                        dateFrom.lastIndexOf('.'))),
                Integer.parseInt(dateFrom.substring(0, dateFrom.indexOf('.'))));
        LocalDate dateToLocal = LocalDate.of(Integer.parseInt(dateTo.substring(dateTo
                        .lastIndexOf('.') + 1,
                dateTo.lastIndexOf('.') + 5)),
                Integer.parseInt(dateTo.substring(dateTo.indexOf('.') + 1,
                        dateTo.lastIndexOf('.'))),
                Integer.parseInt(dateTo.substring(0, dateTo.indexOf('.'))));
        LocalDate dataDate;
        int[] salary = new int[names.length];
        Arrays.fill(salary, 0);
        for (String strData: data) {
            dataDate = LocalDate.of(Integer.parseInt(strData.substring(strData
                            .lastIndexOf('.') + 1,
                    strData.lastIndexOf('.') + 5)),
                    Integer.parseInt(strData.substring(strData.indexOf('.') + 1,
                            strData.lastIndexOf('.'))),
                    Integer.parseInt(strData.substring(0, strData.indexOf('.'))));
            if ((dataDate.isAfter(dateFromLocal) || dataDate.isEqual(dateFromLocal))
                    && (dataDate.isBefore(dateToLocal) || dataDate.isEqual(dateToLocal))) {
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
            allInfo.append(System.lineSeparator()).append(names[i]).append(" - ").append(salary[i]);
        }
        return allInfo.toString();
    }
}
