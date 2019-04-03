public class Main {
    public static void main(String[] args) {
        String login, pass, auth, authFromClient = "auth_login_pass";

        //new StartServer; //запускаем сервак
        auth = String.valueOf(authFromClient.split("_")[0]);
        login = authFromClient.substring(authFromClient.indexOf("_") + 1, authFromClient.length()).split("_")[0];
        pass = authFromClient.substring(authFromClient.lastIndexOf("_") + 1, authFromClient.length());
        System.out.println(auth + " " + login + " " + pass);
    }
}
