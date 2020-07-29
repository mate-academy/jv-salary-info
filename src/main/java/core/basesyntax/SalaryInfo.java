package core.basesyntax;

import core.basesyntax.core.basesyntax.exception.IllegalDateParametersException;

public class SalaryInfo {
    /**
     * <p>Реализуйте метод getSalaryInfo(String[] names, String[] data,
     * String dateFrom, String dateTo)
     * вычисляющий зарплату сотрудников. На вход методу подаётся 2 массива и 2 даты,
     * определяющие период за который надо вычислить зарплату, первый массив содержит имена
     * сотрудников организации, второй массив информацию о рабочих часах и ставке. Формат данных
     * второго массива следующий: дата, имя сотрудника, количество отработанных часов,
     * ставка за 1 час. Метод должен вернуть отчёт за период, который передали в метод
     * (обе даты включительно) составленный по следующей форме: Отчёт за период
     * #дата_1# - #дата_2# Имя сотрудника - сумма заработанных средств за этот период
     * Создать пакет core.basesyntax.core.basesyntax.exception
     * и в нём класс-ошибку IllegalDateParametersException. Сделать так,
     * чтобы метод getSalaryInfo выбрасывал IllegalDateParametersException,
     * если dateFrom > dateTo, с сообщнием "Wrong parameters"</p>
     *
     * <p>Пример ввода: date from = 01.04.2019 date to = 30.04.2019</p>
     *
     * <p>names:
     * Сергей
     * Андрей
     * София</p>
     *
     * <p>data:
     * 26.04.2019 Сергей 60 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50
     * 26.04.2019 Андрей 3 200
     * 26.04.2019 Сергей 7 100
     * 26.04.2019 София 9 100
     * 26.04.2019 Сергей 11 50</p>
     *
     * <p>Пример вывода:
     * Отчёт за период 01.04.2019  - 30.04.2019
     * Сергей - 1550
     * Андрей - 600
     * София - 900</p>
     */
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo)
            throws Exception {

        StringBuilder sb = new StringBuilder();
        sb.append("Отчёт за период ").append(dateFrom).append(" - ").append(dateTo);

        for (String myEmployeeName : names) {
            int earnedMoney = 0;
            for (String myWorkingData : data) {

                String[] dataArray = myWorkingData.split(" ");
                int beginDate = convertStringDate(dateFrom.split("\\."));
                int endDate = convertStringDate(dateTo.split("\\."));
                int currentDate = convertStringDate(dataArray[0].split("\\."));

                if (beginDate > endDate) {
                    throw new IllegalDateParametersException("Wrong parameters");
                }

                if (beginDate <= currentDate
                        && endDate >= currentDate
                        && myEmployeeName.equalsIgnoreCase(dataArray[1])) {
                    earnedMoney += Integer.parseInt(dataArray[2]) * Integer.parseInt(dataArray[3]);
                }
            }
            sb.append("\n").append(myEmployeeName).append(" - ").append(earnedMoney);
        }
        return sb.toString();
    }

    //Doing reverse for array with date strings to int.
    public int convertStringDate(String[] stringDate) {
        StringBuilder myStringBuilder = new StringBuilder();
        for (int i = stringDate.length - 1; i >= 0; i--) {
            myStringBuilder.append(stringDate[i]);
        }
        return Integer.parseInt(myStringBuilder.toString());
    }
}
