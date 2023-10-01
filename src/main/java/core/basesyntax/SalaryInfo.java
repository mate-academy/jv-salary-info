package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        HashMap<String, Integer> reportInfo = new HashMap<>(); //create values
        Date fromDate = null; //that I will work with later
        Date toDate = null;
        String[] splitDate = null;
        Date workDate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            fromDate = simpleDateFormat.parse(dateFrom); // I convert the interval
            toDate = simpleDateFormat.parse(dateTo); // to a date to make it easier to compare
        } catch (ParseException e) {
            throw new RuntimeException("can't turn to date " + e);
        }
        for (String name : names) { // fill in the hash map with names
            reportInfo.put(name, 0); // in case the person did not work in the interim
        }
        for (String date : data) { // I get the variables line by line from data
            splitDate = date.split(" "); // I divide the line by spaces
            try { // and transfer the date to the date
                workDate = simpleDateFormat.parse(splitDate[0]);
            } catch (ParseException e) {
                throw new RuntimeException("Can't turn workDate to Date" + e);
            }
            if ((workDate.after(fromDate) // I check if the date is in that interval
                    || workDate.equals(fromDate))
                    && (workDate.before(toDate)
                    || workDate.equals(toDate))) {
                reportInfo.put(splitDate[1], //add the name and amount of the salary to the map
                        reportInfo.get(splitDate[1])
                                + Integer.parseInt(splitDate[2])
                                * Integer.parseInt(splitDate[3]));
            }
        }
        StringBuilder stringBuilder = new StringBuilder("Report for period ") // create an output
                .append(dateFrom)
                .append(" - ")
                .append(dateTo);
        for (String name : names) {
            stringBuilder.append(System.lineSeparator()).append(name).append(" - ")
                    .append(reportInfo.get(name));
        }

        return stringBuilder.toString();
    }
}
