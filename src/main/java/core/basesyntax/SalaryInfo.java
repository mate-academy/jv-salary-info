package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salaryInfo = new StringBuilder();
        salaryInfo.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        for (String name : names) {
            String user = new String(name);
            int userPayment = 0;
            for (int i = 0; i < data.length; i++) {
                String[] userData = data[i].split(" ");
                try {
                    Date startDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
                    Date endDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
                    Date currentDate = new SimpleDateFormat("dd.MM.yyyy").parse(userData[0]);
                    if (userData[1].equals(user)
                            && currentDate.after(startDate)
                            && currentDate.before(endDate)) {
                        userPayment = userPayment +(Integer.valueOf(userData[2]) * Integer.valueOf(userData[3]));

                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                salaryInfo.append(System.lineSeparator()).append(user).append(" - ").append(userPayment);
            }
            salaryInfo.append(System.lineSeparator());
        }
        return salaryInfo.toString();
    }
}
