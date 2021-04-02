package core.basesyntax;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SalaryInfo {
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salary = new StringBuilder()
                .append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            Calendar from = new GregorianCalendar();
            Calendar to = new GregorianCalendar();
            from.setTime(FORMATTER.parse(dateFrom, new ParsePosition(0)));
            to.setTime(FORMATTER.parse(dateTo, new ParsePosition(0)));
            salary.append("\n").append(name).append(" - ");
            int totalSalary = 0;
            while (!from.after(to)) {
                String dateAndName = FORMATTER.format(from.getTimeInMillis())
                        + " " + name + " ";
                for (String line : data) {
                    if (line.contains(dateAndName)) {
                        line = line.replaceAll(dateAndName, "");
                        String[] hoursAndRate = line.split(" ");
                        totalSalary += Integer.parseInt(hoursAndRate[0])
                                * Integer.parseInt(hoursAndRate[1]);
                    }
                }
                from.add(Calendar.DAY_OF_MONTH, +1);
            }
            salary.append(totalSalary);
        }
        return salary.toString();
    }

}
