package ru.dzhenenko.view;

import ru.dzhenenko.service.*;

import java.sql.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TerminalView {
    private static AccountService accountService;
    private static AccountTypeService accountTypeService;
    private static AuthService authService;

    public TerminalView(AccountService accountService, AccountTypeService accountTypeService, AuthService authService) {
        this.accountService = accountService;
        this.accountTypeService = accountTypeService;
        this.authService = authService;
    }
    public static void start(long userId1, UserDTO userDto) throws SQLException, ParseException {
        Scanner scanner = new Scanner(System.in);
        boolean menuStatus = true;
        while (menuStatus) {
            System.out.println("MENU:");
            System.out.println("1: Показать счета");
            System.out.println("2: Показать транзакции");
            System.out.println("3: Показать отчет по категориям");
            System.out.println("0: Выход");
            int input = scanner.nextInt();
            switch (input) {
                case 1:
//------------------------------------------------------------------------------
                    System.out.println("Твои счета: ");
                    AccountService accountService1 = ServiceFactory.getAccountService();

                    List<AccountDTO> userAccounts = accountService1.viewAccount(userId1);
                    for (AccountDTO userAccount : userAccounts) {
                        System.out.println(userAccount);
                    }


                        System.out.println("1: Создать счет");
                        System.out.println("2: Удалить счет");
                        System.out.println("3: Выход");
                        input = scanner.nextInt();
                        switch (input) {
                            case 1:
                                // Создать счет
                                if (input == 1) {
                                    System.out.println("Пожалуйста, введите детали счета:");
                                    System.out.println("Имя:");
                                    Scanner nameScan = new Scanner(System.in);
                                    String name1 = nameScan.nextLine();

                                    System.out.println("Баланс:");
                                    Scanner balScan = new Scanner(System.in);
                                    int balance1 = balScan.nextInt();
                                    long testId4 = userDto.getId();

                                    // метод создания счета
                                    accountService = ServiceFactory.getAccountService();
                                    accountService.createAccount(name1, balance1, testId4);

                                    //AccountDTO accountDTO = accountService1.createAccount(name1, balance1, testId4);
                                    System.out.println("Your account is successfully created!"); //+ accountDTO);

                                }

                                break;
                            case 2:
                                System.out.println("**** УДАЛЕНИЕ СЧЕТА ****");
                                System.out.println("************************");
                                Scanner scanner2 = new Scanner(System.in);
                                System.out.println("Введите имя счета: ");
                                String name1 = scanner2.nextLine();

                                System.out.println("Your account is successfully deleted!");

                                // метод удаления счета
                                accountService = ServiceFactory.getAccountService();
                                accountService.removeAccount(name1);

                                break;
                            case 3:
                                System.out.println("GOOD BYE!");
                                break;
                        }
                        break;
                        // просмотр отчетов
                case 3:
                    System.out.println("************************");
                    System.out.println("Введите начальную дату DD-MM-YYYY");
                    Scanner scannerF = new Scanner(System.in);
                    String firstDate = scannerF.next();

                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("YYYY-MM-DD");
                    Date parsedDate1 = dateFormat1.parse(firstDate);
                    Timestamp date1 = new java.sql.Timestamp(parsedDate1.getTime());

                    System.out.println("ВВедите конечную дату DD-MM-YYYY");
                    Scanner scannerS = new Scanner(System.in);
                    String secondDate = scannerS.next();

                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("YYYY-MM-DD");
                    Date parsedDate2 = dateFormat2.parse(secondDate);
                    Timestamp date2 = new java.sql.Timestamp(parsedDate2.getTime());

                    long testId7 = userDto.getId();
                    System.out.println("Отчет: ");
                    ReportByCategoryService reportByCategoryService = ServiceFactory.getReportByCategoryService();
                    reportByCategoryService.viewReportCategory(testId7,date1,date2);

                    ReportByCategoryService accountService2 = ServiceFactory.getReportByCategoryService();
                    List<ReportByCategoryDTO> list = accountService2.viewReportCategory(testId7,date1,date2);

                    for (ReportByCategoryDTO userCategory : list) {
                        System.out.println(userCategory);
                    }
                    break;
                case 2:
                    System.out.println("**********************************");
                    System.out.println("====== КАТЕГОРИИ ТРАНЗАКЦИЙ ======");
                    System.out.println();
                    System.out.println("1: Создать транзакцию");
                    System.out.println("2: Удалить транзакцию");
                    System.out.println("3: Редактировать транзакцию");
                    System.out.println("0: Выход");
                    input = scanner.nextInt();

                    switch (input) {

                        case 1:
                            if (input == 1) {

                                System.out.println("*****************************");
                                System.out.println("==== СОЗДАНИЕ ТРАНЗАКЦИИ ====");

                                Scanner scanner3 = new Scanner(System.in);
                                System.out.println("Введите тип транзакции: ");
                                String name2 = scanner3.nextLine();

                                System.out.println("Your type account is successfully created!");
                                // метод создания транзакции
                                AccountTypeService accountTypeService = ServiceFactory.getAccountTypeService();
                                accountTypeService.createTypeAccount(name2);
                            }
                            break;
                        case 2:
                            System.out.println("*****************************");
                            System.out.println("==== УДАЛЕНИЕ ТРАНЗАКЦИИ ====");

                            Scanner scanner3 = new Scanner(System.in);
                            System.out.println("Введите тип транзакции: ");
                            String name3 = scanner3.nextLine();
                            System.out.println("Your type account is successfully created!");

                            // метод удаления транзакции
                            AccountTypeService accountTypeService = ServiceFactory.getAccountTypeService();
                            accountTypeService.removeAccountType(name3);

                            break;
                        case 3:
                            System.out.println("******************************");
                            System.out.println("= РЕДАКТИРОВАНИЕ ТРАНЗАКЦИИ ==");

                            String oldName = request("Введите имя для редактирования:");
                            int idBD = requestId("Ведите id:");
                            String newName = request("Введите новое имя:");
                            System.out.println("Транзакция успешно изменена c " + oldName + " на " + newName + " c id " + idBD + "!");

                            // метод редактирования транзакции
                            accountTypeService = ServiceFactory.getAccountTypeService();
                            accountTypeService.editingAccountType(newName, idBD);
                            break;
                //case 3:
                case 0:
                            System.out.println("GOOD BYE!");
                            menuStatus = false;
                            break;
                        default:
                            System.out.println("Не понятный ввод!");
                    }
            }
        }
    }


    static String request(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(title);
        return scanner.nextLine();
    }

    static Integer requestId(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(title);
        return scanner.nextInt();
    }


}
