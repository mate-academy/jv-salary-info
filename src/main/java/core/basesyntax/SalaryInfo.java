package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SalaryInfo {
    private int[] salary = null;

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        salary = new int[names.length];
        splitData(names, data, dateFrom, dateTo);
        StringBuffer dateInfo = new StringBuffer();
        StringBuffer salaryInfo = new StringBuffer();
        dateInfo.append("Report for period ")
                .append(dateFrom)
                .append(" - ").append(dateTo)
                .append("\n");
        for (int i = 0; i < 3; i++) {
            salaryInfo.append(names[i])
                    .append(" - ")
                    .append(salary[i]);
            if (i != 2) {
                salaryInfo.append("\n");
            }
        }
        return dateInfo.toString() + salaryInfo.toString();
    }

    private void splitData(String[] names, String[] data, String dateFrom, String dateTo) {
        Date dateFromPeriod = parseDate(dateFrom);
        Date dateToPeriod = parseDate(dateTo);
        for (String line : data) {
            String[] splitLine = line.split(" ");
            Date dateCurrentLine = parseDate(splitLine[0]);
            if (isDateInInterval(dateCurrentLine, dateFromPeriod, dateToPeriod)) {
                assignSalary(names, splitLine[1], splitLine[2], splitLine[3]);
            }
        }
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = null;

        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Can't parse the date" + e);
        }
        return date;
    }

    private Boolean isDateInInterval(Date dateNow, Date dateFrom, Date dateTo) {
        return (dateNow.after(dateFrom) || dateNow.equals(dateFrom))
                && (dateNow.before(dateTo) || dateNow.equals(dateTo));
    }

    private void assignSalary(String[] name, String nameWorker, String numberOfHours,
                              String salaryFromHour) {
        int numberHours = Integer.parseInt(numberOfHours);
        int salaryHour = Integer.parseInt(salaryFromHour);
        for (int i = 0; i < name.length; i++) {
            if (nameWorker.equals(name[i])) {
                salary[i] += numberHours * salaryHour;
                break;
            }
        }
    }
}
