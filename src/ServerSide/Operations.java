import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;

class Operations {

    private static Connection conn1;
    private static final String DB_URL = "jdbc:oracle:thin:@10.68.17.239:1521:tfido";
    private static final String DB_PASSWORD = "123";
    private static final String DB_LOGIN = "DBOLT";
    static String clientName = null;

    String getAuthDate(Scanner in, PrintWriter out) throws SQLException, ClassNotFoundException { // дает результат аутентификации
        String authFromClient, login, pass, authResult, operation;

        if(in.hasNext()){
            authFromClient = in.nextLine();
            operation = authFromClient.split("_")[0];
            if(operation.equalsIgnoreCase("auth")) {
                login = String.valueOf(authFromClient.split("_")[6]);
                pass = authFromClient.substring(authFromClient.indexOf("_") + 1, authFromClient.length());
                connectToDataBase();
                authResult = clientAuth(login, pass, out); // запускаем саму аутентификацию
            } else {

            }
        } else {
            closeConn();
            return "2";
        }
        /*if(authResult.equalsIgnoreCase("done")){
            clientName = login;
            return "1";
        } else*/
        return "0";
    }

    private void connectToDataBase() throws ClassNotFoundException, SQLException {
        Class.forName("oracle.jdbc.OracleDriver");
        conn1 = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD); // url, login, password
        if(conn1 != null){
            System.out.println("Connect");
        }
    }

    private String clientAuth(String login, String pass, PrintWriter out) throws SQLException {

        String authResult = "Fail";
        String requesttoDataBase = "select CORRESPONDENT_ACCOUNT, CITY from NAME_COUNTER";
        Statement stmt = conn1.createStatement();
        ResultSet resultSet = stmt.executeQuery(requesttoDataBase);

        while (resultSet.next()){
            if(login.equals(resultSet.getString(1)) && pass.equals(resultSet.getString(2))) {authResult = "done"; break;}
            if((login.equals(resultSet.getString(1)) && !pass.equals(resultSet.getString(2))) ||
            (!login.equals(resultSet.getString(1)) && pass.equals(resultSet.getString(2)))) {authResult = "Pass or login is wrong"; break;}
        }
        sendMsg(authResult, out);
        sqlClose(stmt, resultSet);
        return authResult;
    }

    void sendMessaageToClient(String msg, String abonent, PrintWriter out){ // перебор клиентов до необходимого
        for(ClientThread clientThread : StartServer.clientThreads) {
            if(clientThread.name.equalsIgnoreCase(abonent)){
                System.out.println(msg);
                sendMsg(msg, out);
            }
        }
    }

    private void sendMsg(String msg, PrintWriter out){ // отправка сообщения клиенту
        try {
            out.println(msg);
            out.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    void removeClient (ClientThread clientThread){
        StartServer.clientThreads.remove(clientThread);
    } //удаляем клиента из списка

    private void sqlClose(Statement stmt, ResultSet resultSet) throws SQLException {
        stmt.close();
        resultSet.close();
    }

    private static void closeConn(){
        try {
            if(conn1 != null){
                conn1.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("\ngoodbay");
    }
}