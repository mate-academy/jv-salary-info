package core.basesyntax;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
pattern
public class SalaryInfo {
    private final String pattern = "dd.MM.yyyy";
    private final SimpleDateFormat formatter = new SimpleDateFormat(pattern);

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder res = new StringBuilder();
        try {
            LocalDate date1 = formatter.parse(dateFrom).toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate date2 = formatter.parse(dateTo).toInstant()
                    .atZone(ZoneId.systemDefault()).toLocalDate();
            if (date2.compareTo(date1) < 0) {
                throw new ParseException("DateFrom occurs after DateTo", 0);
            }
            DateFormat dateFormat;
            dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.GERMANY);
            res.append("Report for period " + dateFormat.format(Date.from(date1.atStartOfDay()
                    .atZone(ZoneId.systemDefault()).toInstant())) + " - "
                    + dateFormat.format(Date.from(date2.atStartOfDay()
                    .atZone(ZoneId.systemDefault()).toInstant())));
            for (String n: names) {
                int money = 0;
                for (String d: data) {
                    var temp = d.split(" ");
                    var dtmp = formatter.parse(temp[0]).toInstant().atZone(ZoneId.systemDefault())
                            .toLocalDate();
                    if ((date1.compareTo(dtmp) < 0 || date1.compareTo(dtmp) == 0) 
                            && (date2.compareTo(dtmp) > 0 || date2.compareTo(dtmp) == 0)
                            && n.trim().equals(temp[1].trim())) {
                        money += Integer.parseInt(temp[2]) * Integer.parseInt(temp[3]);
                    }
                }
                res.append("\n" + n.trim() + " - " + String.format("%d", money));
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return res.toString();
    }
}
