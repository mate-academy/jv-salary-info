package core.basesyntax;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ").append(dateTo);
        if (names != null && data != null) {
            for (String name: names) {
                int moneyEarned = 0;
                builder.append(System.getProperty("line.separator"));
                for (String dates : data) {
                    String[] dataToMath = dates.split(" ");
                    boolean dateCheck = checkDate(dateFrom, dateTo, dataToMath[0]);
                    if (name.equals(dataToMath[1]) && checkDate(dateFrom, dateTo, dataToMath[0])) {
                        moneyEarned += Integer.parseInt(dataToMath[2])
                                * Integer.parseInt(dataToMath[3]);
                    }
                }
                builder.append(name).append(" - ").append(moneyEarned);
            }
        }
        return builder.toString();
    }

    private boolean checkDate(String dateFrom, String dateTo, String dateOfWork) {
        Date dateFromToDate = createDate(dateFrom);
        Date dateToToDate = createDate(dateTo);
        Date dayOfWork = createDate(dateOfWork);
        return (dayOfWork.after(dateFromToDate) || dayOfWork.equals(dateFromToDate))
                && (dayOfWork.before(dateToToDate) || dayOfWork.equals(dateToToDate));
    }

    private final Date createDate(String dateInString) {
        try {
            Date date = new SimpleDateFormat("dd.MM.yyyy").parse(dateInString);
            return date;
        } catch (Exception e) {
            throw new RuntimeException("Date" + dateInString + "is incorrect", e);
        }
    }
}

