package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    public static final int DATE_POSITION_IN_ARRAY = 0;
    public static final int NAME_POSITION_IN_ARRAY = 1;
    public static final int HOURS_POSITION_IN_ARRAY = 2;
    public static final int HOURLY_RATE_POSITION_IN_ARRAY = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        Date dateto;
        Date datefrom;
        try {
            dateto = new SimpleDateFormat("dd.MM.yyyy").parse(dateTo);
            datefrom = new SimpleDateFormat("dd.MM.yyyy").parse(dateFrom);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String[] nameArray = new String[names.length];
        Integer[] salaryArray = new Integer[names.length];
        StringBuilder report = new StringBuilder();
        report.append("Report for period ").append(dateFrom).append(" - ").append(dateTo)
                .append("\n");
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            salaryArray[i] = 0;
            nameArray[i] = name;
            for (String info : data) {
                String[] parts = info.split(" ");
                Date actualDate;
                try {
                    actualDate = new SimpleDateFormat("dd.MM.yyyy")
                            .parse(parts[DATE_POSITION_IN_ARRAY]);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                if (parts[NAME_POSITION_IN_ARRAY].equals(name)
                        && ((actualDate.before(dateto) || actualDate.equals(dateto))
                                && (actualDate.after(datefrom) || actualDate.equals(datefrom)))) {
                    salaryArray[i] += Integer.parseInt(parts[HOURS_POSITION_IN_ARRAY])
                            * Integer.parseInt(parts[HOURLY_RATE_POSITION_IN_ARRAY]);
                }
            }
            report.append(nameArray[i]).append(" - ").append(salaryArray[i]);
            if (i != (names.length - 1)) {
                report.append(System.lineSeparator());
            }
        }
        return report.toString();
    }
}
