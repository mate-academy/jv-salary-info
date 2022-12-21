package core.basesyntax;

public class SalaryInfo {
    private DataHandler dh = new DataHandler();
    private DateToNumber dt = new DateToNumber();

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        dh.handler(data);
        String[] namesData = dh.getNamesData();
        String[] dates = dh.getDates();
        String[] hours = dh.getHours();
        String[] income = dh.getIncome();
        int[] salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if (names[i].equals(namesData[j])
                        && dt.getDayNumber(dateFrom) <= dt.getDayNumber(dates[j])
                        && dt.getDayNumber(dateTo) >= dt.getDayNumber(dates[j])) {
                    salary[i] += Integer.parseInt(hours[j]) * Integer.parseInt(income[j]);
                }
            }
        }
        StringBuilder stb = new StringBuilder();
        stb.append("Report for period ");
        stb.append(dateFrom);
        stb.append(" - ");
        stb.append(dateTo);
        stb.append(System.lineSeparator());

        for (int i = 0; i < names.length; i++) {
            stb.append(names[i]);
            stb.append(" - ");
            stb.append(salary[i]);
            if (i == names.length - 1) {
                break;
            }
            stb.append(System.lineSeparator());
        }

        return stb.toString();
    }
}
