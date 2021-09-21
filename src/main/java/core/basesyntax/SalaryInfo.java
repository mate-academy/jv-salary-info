package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String patternFormat = "dd.MM.yyyy";
        Locale localeUa = new Locale("ua", "UA");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patternFormat).withLocale(localeUa);

        LocalDate dateBegin;
        try {
            dateBegin = LocalDate.parse(dateFrom, formatter);
        } catch (Exception e) {
            System.out.println("Wrong dateFrom! " + e);
            return "";
        }
        LocalDate dateEnd;
        try {
            dateEnd = LocalDate.parse(dateTo, formatter);
        } catch (Exception e) {
            System.out.println("Wrong dateTo! " + e);
            return "";
        }
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            int amount = 0;
            for (String employeeData : data) {
                String[] dataArray = employeeData.split(" ");
                LocalDate dataDate;
                try {
                    dataDate = LocalDate.parse(dataArray[0], formatter);
                } catch (Exception e) {
                    System.out.println("Wrong date! " + e);
                    continue;
                }
                if (employeeData.contains(name) && !dataDate.isBefore(dateBegin) && !dataDate.isAfter(dateEnd)) {
                    amount += Integer.parseInt(dataArray[2]) * Integer.parseInt(dataArray[3]);
                }
            }
            report.append(System.lineSeparator()).append(name).append(" - ").append(amount);
        }
        return report.toString();
    }
}
