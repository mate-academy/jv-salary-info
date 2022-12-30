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
                String[] arrayOfData = strData.split(" ");
                dataDate = LocalDate.parse(arrayOfData[0], formatter);
                if ((dataDate.isAfter(dateFromLocal) || dataDate.isEqual(dateFromLocal))
                        && (dataDate.isBefore(dateToLocal) || dataDate.isEqual(dateToLocal))) {
                    if (name.equals(arrayOfData[1])) {
                        int workHours = Integer.parseInt(arrayOfData[2]);
                        int salaryPerHour = Integer.parseInt(arrayOfData[3]);
                        salary += workHours * salaryPerHour;
                    }
                }
            }
            allInfo.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return allInfo.toString();
    }
}
