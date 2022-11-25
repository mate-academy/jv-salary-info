package core.basesyntax;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        StringBuilder salariesAndNames = new StringBuilder();
        StringBuilder sbDone = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            if (compareDates(dateFrom, dateTo, data[i].split(" ")[0])) {
                String[] oneDateArray = data[i].split(" ");
                int salaryAmount = parseInt(oneDateArray[2]) * parseInt(oneDateArray[3]);

                for (String name : names) {
                    if (oneDateArray[1].equals(name)) {
                        salariesAndNames.append(salaryAmount)
                                .append(" ").append(name).append("/");
                    }
                }
            }
        }
        String[] arrayOfCorrectValues = salariesAndNames.toString().split("/");
        sbDone.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");

        String indentDown = "\n";
        for (int i = 0; i < names.length; i++) {
            if (i == names.length - 1) {
                indentDown = "";
            }
            sbDone.append(names[i]).append(" - ")
                    .append(getTheFullAmount(arrayOfCorrectValues, names, i))
                    .append(indentDown);
        }
        return sbDone.toString();
    }

    public static boolean compareDates(String dateFrom, String dateTo, String dateCheck) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date checkDate = null;
        try {
            checkDate = sdf.parse(dateCheck);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Date dateStart = null;
        try {
            dateStart = sdf.parse(dateFrom);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Date dateEnd = null;
        try {
            dateEnd = sdf.parse(dateTo);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (checkDate.after(dateStart) && checkDate.before(dateEnd)) {
            return true;
        }
        if (checkDate.before(dateStart) || checkDate.after(dateEnd)) {
            return false;
        }
        if (checkDate.equals(dateStart) || checkDate.equals(dateEnd)) {
            return true;
        }
        return true;
    }

    public int getTheFullAmount(String[] arrayInfo, String[] names, int counter) {
        int generalNumber = 0;
        for (String s : arrayInfo) {
            if (s.contains(names[counter])) {
                generalNumber += parseInt(s.replaceAll("[^0-9]", ""));
            }
        }
        return generalNumber;
    }
}
