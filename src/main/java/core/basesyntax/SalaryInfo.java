package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int sumJohn = 0;
        int sumAndrew = 0;
        int sumKate = 0;

        StringBuilder sb = new StringBuilder();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date to;
        Date from;
        try {
            to = simpleDateFormat.parse(dateTo);
            from = simpleDateFormat.parse(dateFrom);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        for (String datum : data) {
            String[] dates = datum.split(" ");
            Date d1;
            try {
                d1 = simpleDateFormat.parse(dates[0]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (d1.compareTo(from) >= 0 && d1.compareTo(to) <= 0) {
                switch (dates[1]) {
                    case "John":
                        sumJohn += Integer.parseInt(dates[2]) * Integer.parseInt(dates[3]);
                        break;
                    case "Andrew":
                        sumAndrew += Integer.parseInt(dates[2]) * Integer.parseInt(dates[3]);
                        break;
                    case "Kate":
                        sumKate += Integer.parseInt(dates[2]) * Integer.parseInt(dates[3]);
                        break;
                    default:
                        break;
                }
            }
        }
        return sb.append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo).append(System.lineSeparator())
                .append(names[0]).append(" - ").append(sumJohn).append(System.lineSeparator())
                .append(names[1]).append(" - ").append(sumAndrew).append(System.lineSeparator())
                .append(names[2]).append(" - ").append(sumKate)
                .toString();
    }
}
