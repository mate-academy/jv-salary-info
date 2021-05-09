package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SalaryInfo {

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String result = "Report for period ";
        int[] salary = new int[names.length];

        Date date1 = null;
        Date date2 = null;

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        try {
            date1 = format.parse(dateFrom);
            date2 = format.parse(dateTo);
        } catch (ParseException e) {
            System.out.println("Date no correct");
        }
        result = result + dateFrom + " - " + dateTo + "\n";

        for (String temp:data) {
            Date date = null;
            try {
                date = format.parse(part(temp));
            } catch (ParseException e) {
                System.out.println("Date no correct");
            }
            temp = newText(temp);
            String name = part(temp);
            temp = newText(temp);
            int hour;
            int salaryHour;
            hour = Integer.parseInt(part(temp));
            temp = newText(temp);
            salaryHour = Integer.parseInt(temp);
            try {
                assert date != null;
                if (date.after(date1) || date.equals(date1)) {
                    if (date.before(date2) || date.equals(date2)) {
                        for (int j = 0; j < names.length; j++) {
                            if (names[j].equals(name)) {
                                salary[j] = salary[j] + (salaryHour * hour);
                            }
                        }
                    }
                }
            } catch (NullPointerException e) {
                e.getMessage();
            }

        }
        for (int i = 0; i < names.length; i++) {
            result = formatResult(result, names[i], salary[i]);
        }
        return result.strip();
    }

    private String part(String parameter) {
        return parameter.substring(0, parameter.indexOf(' ')).strip();
    }

    private String newText(String oldText) {
        return oldText.replaceFirst(oldText.substring(0, oldText.indexOf(' ')), "").strip();
    }

    private String formatResult(String text, String name, int salary) {
        return text + name + " - " + salary + "\n";
    }
}
