package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder("Report for period "
                + dateFrom + " - " + dateTo);

        for (String name : names) {
            int salary = 0;

            for (String getData : data) {
                String[] infoData = getData.split(" ");
                if (isInDate(infoData[0], dateFrom, dateTo)) {
                    salary += calculateSalary(infoData, name);
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }

        return salaryInfo.toString();
    }

    private int calculateSalary(String[] infoData, String name) {
        if (infoData[1].equals(name)) {
            return Integer.parseInt(infoData[2]) * Integer.parseInt(infoData[3]);
        }
        return 0;
    }

    private boolean isInDate(String dataDate, String dateFrom, String dateTo) {
        Date parseDateData = null;
        Date parseDateFrom = null;
        Date parseDateTo = null;

        try {
            parseDateData = new SimpleDateFormat("dd.MM.yyyy").parse(dataDate);
            parseDateFrom = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
            parseDateTo = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
        } catch (ParseException e) {
            System.out.println("Date format parsing exception");
        }

        return parseDateTo != null && parseDateData.compareTo(parseDateFrom) >= 0
                && parseDateData.compareTo(parseDateTo) <= 0;
    }
}
