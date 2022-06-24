package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);

        for (String name : names) {
            int userPayment = 0;
            for (String employeeData : data) {
                String[] userData = employeeData.split(" ");
                if (isDateInPeriod(userData[0], dateFrom, dateTo)) {
                    userPayment = userPayment + calculationPayment(userData, name);
                }
            }
            salaryInfo.append(System.lineSeparator()).append(name).append(" - ").append(userPayment);
        }
        return salaryInfo.toString();
    }

    private int calculationPayment (String[] userData, String name) {
        int result = 0;
        if (userData[1].equals(name)) {
            result = Integer.valueOf(userData[2]) * Integer.valueOf(userData[3]);
        }
        return result;
    }

    private boolean isDateInPeriod(String dataDate, String dateFrom, String dateTo) {
        Date currentDate = null;
        Date startDate = null;
        Date endDate = null;

        try {
            currentDate = new SimpleDateFormat("dd.MM.yyyy").parse(dataDate);
            startDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
            endDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
        } catch (ParseException e) {
            System.out.println("Date entry error!");
            e.printStackTrace();
        }
        return endDate != null && currentDate.after(startDate) && currentDate.before(endDate);
    }
}
