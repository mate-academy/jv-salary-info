package core.basesyntax;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SalaryInfo {
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder report = new StringBuilder()
                .append("Report for period ").append(dateFrom)
                .append(" - ").append(dateTo);
        for (String name : names) {
            Calendar from = new GregorianCalendar();
            Calendar to = new GregorianCalendar();
            from.setTime(FORMATTER.parse(dateFrom, new ParsePosition(0)));
            to.setTime(FORMATTER.parse(dateTo, new ParsePosition(0)));
            report.append("\n").append(name).append(" - ");
            int salary = 0;
            for (String line : data) {
                if (line.contains(name)) {
                    Calendar currentDate = new GregorianCalendar();
                    currentDate.setTime(FORMATTER.parse(line.split(" ")[0], new ParsePosition(0)));
                    if ((currentDate.equals(from) || currentDate.after(from))
                            && (currentDate.equals(to) || currentDate.before(to))) {
                        salary += Integer.parseInt(line.split(" ")[2])
                                * Integer.parseInt(line.split(" ")[3]);
                    }
                }
            }
            report.append(salary);
        }
        return report.toString();
    }
}
