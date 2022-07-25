package core.basesyntax;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private final int DATES_LIST = 0;
    private final int NAMES_LIST = 1;
    private final int WORKING_HOURS = 2;
    private final int INCOME_PER_HOUR = 3;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
        Date dateFromFormated = formatDate.parse(dateFrom);
        Date dateToFormated = formatDate.parse(dateTo);
        StringBuilder result = new StringBuilder().append("Report for period ")
                .append(dateFrom).append(" - ").append(dateTo);

        for (int j = 0; j < names.length; j++) {
            String name = names[j];
            int salary =0;

            for (int i = 0; i < data.length; i++) {
                String[] dataSeparate = data[i].split(" ");
                Date dateFromData = formatDate.parse(dataSeparate[DATES_LIST]);

                if (name.equals(dataSeparate[NAMES_LIST])
                        && dateFromData.before(dateToFormated)
                        && dateFromData.after(dateFromFormated) ) {
                    salary += Integer.parseInt(dataSeparate[WORKING_HOURS])
                            * Integer.parseInt(dataSeparate[INCOME_PER_HOUR]);
                    }
                }
            result.append(System.lineSeparator()).append(name).append(" - ").append(salary);
        }
        return result.toString();
    }
}
