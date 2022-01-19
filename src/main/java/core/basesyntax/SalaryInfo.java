package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Date dateFromToDate = createDate(dateFrom);
        Date dateToToDate = createDate(dateTo);
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        if (dateFromToDate != null && dateToToDate != null && names != null && data != null) {
            for (String name: names) {
                int moneyEarned = 0;
                builder.append("\n");
                for (String dates : data) {
                    String[] dataToMath = dates.split(" ");
                    if (name.equals(dataToMath[1])) {
                        Date dayOfWork = createDate(dataToMath[0]);
                        if (dayOfWork != null) {
                            if (dayOfWork.equals(dateFromToDate)
                                    || dayOfWork.after(dateFromToDate)) {
                                if (dayOfWork.equals(dateToToDate)
                                        || dayOfWork.before(dateToToDate)) {
                                    moneyEarned += Integer.parseInt(dataToMath[2])
                                            * Integer.parseInt(dataToMath[3]);
                                }
                            }
                        }
                    }
                }
                builder.append(name).append(" - ").append(moneyEarned);
            }
        }
        return builder.toString();
    }

    private Date createDate(String dateInString) {
        try {
            Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateInString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

