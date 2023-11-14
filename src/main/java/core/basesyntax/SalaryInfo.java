package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder calculatedReport = new StringBuilder();
        calculatedReport.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            Date from = dateFormat.parse(dateFrom);
            Date to = dateFormat.parse(dateTo);
            for (String name : names) {
                int salary = 0;
                for (int i = 0; i < data.length; i++) {
                    String[] dataSplit = data[i].split(" ");
                    Date currentDate = dateFormat.parse(dataSplit[0]);
                    if (name.equals(dataSplit[1]) && currentDate.compareTo(to) <= 0
                            && currentDate.compareTo(from) >= 0) {
                        salary += Integer.parseInt(dataSplit[2]) * Integer.parseInt(dataSplit[3]);
                    }
                }
                calculatedReport.append(System.lineSeparator()).append(name).append(" - ")
                        .append(salary);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Wrong date format", e);
        }
        return calculatedReport.toString();
    }
}
