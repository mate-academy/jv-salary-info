package core.basesyntax;

import core.basesyntax.services.ArrayService;
import core.basesyntax.services.CompareDate;
import core.basesyntax.services.ParseService;
import core.basesyntax.services.PrintService;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int[] resultSalary = new int[names.length];
        ArrayService arrayService = new ArrayService(names);
        ParseService parseService = new ParseService();
        CompareDate compareDate = new CompareDate(
                parseService.stringToDate(dateFrom), parseService.stringToDate(dateTo));

        for (String rowData : data) {
            String[] valuesData = rowData.split(" ");
            if (valuesData[0] == null || valuesData[1] == null
                    || valuesData[2] == null || valuesData[3] == null) {
                continue;
            }

            int index = arrayService.indexByValue(valuesData[1]);
            if (index < 0 || !compareDate.isBetween(parseService.stringToDate(valuesData[0]))) {
                continue;
            }

            resultSalary[index] += Integer.parseInt(valuesData[2])
                    * Integer.parseInt(valuesData[3]);
        }

        StringBuilder result = new StringBuilder();
        result.append(PrintService.printHead(dateFrom, dateTo));
        for (int i = 0; i < names.length; i++) {
            result.append(System.lineSeparator())
                    .append(PrintService.printRow(names[i], String.valueOf(resultSalary[i])));
        }
        return result.toString();
    }
}
