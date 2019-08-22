package core.basesyntax;

import java.time.LocalDate;

public class SalaryInfo {

    /**
     * Реализуйте метод getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты, определяющие
     * период за который надо вычислить зарплату, первый массив содержит имена сотрудников
     * организации, второй массив информацию о рабочих часах и ставке. Формат данных второго массива
     * следующий: дата, имя сотрудника, количество отработанных часов, ставка за 1 час. Метод должен
     * вернуть отчёт за период, который передали в метод (обе даты включительно) составленный по
     * следующей форме: Отчёт за период #дата_1# - #дата_2# Имя сотрудника - сумма заработанных
     * средств за этот период
     * <p>
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     * <p>
     * names: Сергей Андрей София
     * <p>
     * data: 26.04.2019 Сергей 60 50 26.04.2019 Андрей 3 200 26.04.2019 Сергей 7 100 26.04.2018
     * София 9 100 26.04.2019 Сергей 11 50 26.04.2019 Андрей 3 200 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100 26.04.2019 Сергей 11 50
     * <p>
     * Пример вывода: Отчёт за период 01.04.2019  - 30.04.2019 Сергей - 1550 Андрей - 600 София -
     * 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        String[] twoDates = new String[2]; // Створив цей массив, так як, CheckStyle
        // кричить, що мае бути видстань миж инициализациею и використанням.
        StringBuilder report = new StringBuilder();
        String [] workers;
        twoDates[0] = dateFrom;
        twoDates[1] = dateTo;
        dateFrom = new StringBuffer(dateFrom).reverse().toString();
        dateTo = new StringBuffer(dateTo).reverse().toString();
        long minDate = Integer.parseInt(dateFrom.replaceAll("[^0-9]", ""));
        long maxDate = Integer.parseInt(dateTo.replaceAll("[^0-9]", ""));

        System.out.println("mindate = " + minDate + "\nmaxdate = " + maxDate);
        if (minDate > maxDate) {
            return null;
        }


        for (String name : names) {
            int salary = 0;
            for (int i = 0; i < data.length; i++) {
                workers = data[i].split(" ");
                workers[0] = new StringBuffer(workers[0]).reverse().toString();
                int minDayForWorker = Integer.parseInt(workers[0].replaceAll("[^0-9]", ""));
                if (minDayForWorker > maxDate) {
                    break;
                }
                if (name.equals(workers[1])) {
                    salary += Integer.parseInt(workers[2]) * Integer.parseInt(workers[3]);
                }
            }
            report.append(name + " - " + salary + "\n");
        }
        String headOfReport = "Отчёт за период " + twoDates[0] + " - " + twoDates[1] + "\n";
        return headOfReport + report;
    }

}
