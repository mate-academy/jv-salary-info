package core.basesyntax;

import static java.lang.Integer.parseInt;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder sbBasic = new StringBuilder();
        StringBuilder sbDone = new StringBuilder();
        GetDateInNumbers getDateInNumbers = new GetDateInNumbers();
        Selection selection = new Selection();

        int dateMin = getDateInNumbers.getDateInNumbers(dateFrom);
        int dateMax = getDateInNumbers.getDateInNumbers(dateTo);

        for (String c : data) {
            String[] arrayAllInformationData = c.split(" ");
            int theCurrentDate = getDateInNumbers.getDateInNumbers(arrayAllInformationData[0]);

            if (theCurrentDate >= dateMin && theCurrentDate <= dateMax) {
                int counter = parseInt(arrayAllInformationData[2]) *
                        parseInt(arrayAllInformationData[3]);
                for (String name : names) {
                    if (arrayAllInformationData[1].equals(name)) {
                        String cc = (counter + " " + name);
                        sbBasic.append(cc).append("/");
                    }
                }
            }
        }
        String[] lastArray = sbBasic.toString().split("/");

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
                    .append(selection.selection(lastArray, names, i))
                    .append(indentDown);
        }
        return sbDone.toString();
    }
}
