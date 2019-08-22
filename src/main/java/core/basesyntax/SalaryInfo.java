package core.basesyntax;

import java.util.Date;

public class SalaryInfo {
    /**
     * Реализуйте метод getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * <p>
     * Пример ввода: date from = 01.04.2019 date to = 30.04.2019
     * <p>
     * names:
     * Сергей
     * Андрей
     * София
     * <p>
     * data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * <p>
     * Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        if (Integer.parseInt(dateTo.substring(0, 2))
                < Integer.parseInt(dateFrom.substring(0, 2))) {
            return null;
        }
        String infoString = "";
        infoString += "Отчёт за период " + dateFrom + " - " + dateTo + "\n";
        for (int i = 0; i < names.length; i++) {
            int currentWorkerSalary = 0;
            for (String nextData : data) {
                String[] parsedData = nextData.split(" ");
                if (Integer.parseInt(dateTo.substring(0, 2))
                        < Integer.parseInt(parsedData[0].substring(0, 2))) {
                    break;
                }
                if (names[i].equals(parsedData[1])) {
                    currentWorkerSalary += Integer.parseInt(parsedData[2])
                            * Integer.parseInt(parsedData[3]);
                }

            }
            infoString += names[i] + " - " + currentWorkerSalary;
            if (i < names.length) {
                infoString += "\n";
            }
        }
        return infoString;
    }
}
