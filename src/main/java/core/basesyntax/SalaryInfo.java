package core.basesyntax;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        int sum = 0;
        int[] salary = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < data.length; j++) {
                String[] dataPerName = data[j].split(" ");
                String[] dataOfWork = dataPerName[0].split("[.]");
                String[] dateFr = dateFrom.split("[.]");
                String[] dateToo = dateTo.split("[.]");

                if (dataPerName[1].equals(names[i]) && ((Integer.parseInt(dataOfWork[0])
                        >= Integer.parseInt(dateFr[0]) && Integer.parseInt(dataOfWork[0])
                        <= Integer.parseInt(dateToo[0]) && Integer.parseInt(dataOfWork[1])
                        == Integer.parseInt(dateFr[1]) && Integer.parseInt(dataOfWork[1])
                        == Integer.parseInt(dateToo[1])) || (Integer.parseInt(dataOfWork[0])
                        >= Integer.parseInt(dateFr[0]) && Integer.parseInt(dataOfWork[1])
                        == Integer.parseInt(dateFr[1]) && Integer.parseInt(dataOfWork[1])
                        != Integer.parseInt(dateToo[1])) || (Integer.parseInt(dataOfWork[0])
                        <= Integer.parseInt(dateToo[0]) && Integer.parseInt(dataOfWork[1])
                        != Integer.parseInt(dateFr[1]) && Integer.parseInt(dataOfWork[1])
                        == Integer.parseInt(dateToo[1])))) {
                    sum = sum + (Integer.parseInt(dataPerName[2])
                            * Integer.parseInt(dataPerName[3]));
                }
            }
            salary[i] = sum;
            sum = 0;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Report for period ").append(dateFrom).append(" - ")
                .append(dateTo).append("\n");
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < salary.length; j++) {
                if (i == j) {
                    builder.append(names[i]).append(" - ")
                            .append(salary[j]).append("\n");
                }
            }
        }
        String toReturn = builder.toString();
        return toReturn.substring(0, toReturn.length() - 1);
    }
}

