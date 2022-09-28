import java.util.ArrayList;

public final class Main {
    public static void main(String[] args) {
        // Conectando ...
        UserDB userDB = new UserDB();
        userDB.connect();

        // Outros ...
        ArrayList<User> users = new ArrayList<>();
        boolean check;

        // Instanciando Usuários ...
        User user1 = new User("Fabio", "123.456.789-01");
        User user2 = new User("Davi", "987.654.321-02");
        User user3 = new User("Bruno", "123.123.123-03");

        // Inserindo Usuários ...
        check = userDB.insertUser(user1);
        check = userDB.insertUser(user2);
        check = userDB.insertUser(user3);

        // Selecionando Usuários ...
        users = userDB.selectUser();

        // Atualizando 1 Usuário ...
        check = userDB.updateUser(1, "BiO");

        // Deletando 1 Usuário ...
        check = userDB.deleteUser(3);

        // Selecionando Usuários Novamente ...
        users = userDB.selectUser();
    }
}
