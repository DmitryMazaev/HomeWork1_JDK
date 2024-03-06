package client;

public class Client {
    private ClientInterface clientWindow;
    /**
     * Логин клиента
     */
    private String loginClient;
    /**
     * Пароль клиента
     */
    private String passwordClient;
    public String getLoginClient() {
        return loginClient;
    }
    public void setLoginClient(String loginClient) {
        this.loginClient = loginClient;
    }
    public String getPasswordClient() {
        return passwordClient;
    }
    public void setPasswordClient(String passwordClient) {
        this.passwordClient = passwordClient;
    }
    public Client(String loginClient, String passwordClient, ClientInterface clientWindow)
    {
        this.loginClient = loginClient;
        this.passwordClient = passwordClient;
        this.clientWindow = clientWindow;
    }

    /**
     * Отправка чата всем элементам списка клиентов
     */
    public void mailing()
    {
        clientWindow.updateChat();
    }
}
