package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateFromLocal = LocalDate.parse(dateFrom, formatter);
        LocalDate dateToLocal = LocalDate.parse(dateTo, formatter);
        LocalDate dataDate;
        StringBuilder allInfo = new StringBuilder();
        allInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name: names) {
            int salary = 0;
            for (String strData : data) {
                dataDate = LocalDate.parse(strData.substring(0, strData.indexOf(' ')), formatter);
                if ((dataDate.isAfter(dateFromLocal) || dataDate.isEqual(dateFromLocal))
                        && (dataDate.isBefore(dateToLocal) || dataDate.isEqual(dateToLocal))) {
                    if (name.equals(strData.substring(strData.indexOf(' ') + 1,
                            strData.indexOf(' ') + 1 + name.length()))) {
                        int workHours = Integer.parseInt(strData.substring(strData.indexOf(' ') + 2
                                + name.length(), strData.lastIndexOf(' ')));
                        int salaryPerHour = Integer.parseInt(strData.substring(strData
                                .lastIndexOf(' ') + 1));
                        salary = workHours * salaryPerHour + salary;
                    }
                }
            }
            allInfo.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return allInfo.toString();
    }
}
