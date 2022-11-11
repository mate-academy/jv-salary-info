package core.basesyntax;

import static java.lang.Integer.parseInt;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {

        StringBuilder sB_basic = new StringBuilder();
        StringBuilder sB_done = new StringBuilder();
        GetDateInNumbers getDateInNumbers = new GetDateInNumbers();
        Selection selection = new Selection();

        int dateMin = getDateInNumbers.getDateInNumbers(dateFrom);
        int dateMax = getDateInNumbers.getDateInNumbers(dateTo);

        for (String c : data) {
            String[] arrayAllInformationData = c.split(" ");
            int theCurrentDate = getDateInNumbers.getDateInNumbers(arrayAllInformationData[0]);

            if (theCurrentDate >= dateMin && theCurrentDate <= dateMax) {
                int counter = parseInt(arrayAllInformationData[2]) * parseInt(arrayAllInformationData[3]);
                for (String name : names) {
                    if (arrayAllInformationData[1].equals(name)) {
                        String cc = (counter + " " + name);
                        sB_basic.append(cc).append("/");
                    }
                }
            }
        }
        String[] LastArray = sB_basic.toString().split("/");

        sB_done.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append("\n");

        String indentDown = "\n";
        for (int i = 0; i < names.length; i++) {
            if (i == names.length - 1) {
                indentDown = "";
            }

            sB_done.append(names[i]).append(" - ")
                    .append(selection.selection(LastArray, names, i))
                    .append(indentDown);
        }
        return sB_done.toString();
    }
}
