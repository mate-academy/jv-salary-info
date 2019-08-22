package core.basesyntax;

public class SalaryInfo {

    protected String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int dateF = Integer.parseInt(dateFrom.replace(".", ""));
        int dateT = Integer.parseInt(dateTo.replace(".", ""));
        StringBuilder stringBuilder =
                new StringBuilder("Отчёт за период " + dateFrom + " - " + dateTo + '\n');
        if (dateT < dateF) {
            return null;
        }
        for (String s : names) {
            String name;
            int salary;
            int salaryCounter = 0;
            for (String string : data) {
                String day = string.split(" ")[0];
                if (dateF <= Integer.parseInt(day.replace(".", ""))
                        && dateT >= Integer.parseInt(day.replace(".", ""))) {
                    name = string.split(" ")[1];
                    salary = Integer.parseInt(string.split(" ")[2]) *
                            Integer.parseInt(string.split(" ")[3]);
                    if (s.equals(name)) {
                        salaryCounter += salary;
                    }
                }
            }
            stringBuilder.append(s + " - " + salaryCounter + '\n');
        }
        return new String(stringBuilder);
    }
}
