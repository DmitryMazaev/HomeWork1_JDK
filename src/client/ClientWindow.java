package client;

import server.Server;
import server.ServerInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientWindow extends JFrame implements ClientInterface{
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    private JTextField messageField;
    private JButton sendMessage;
    private JPanel messagePanel;
    private TextArea chat;

    private JLabel loginLabel;
    private JTextField loginTextField;
    private JLabel passwordLabel;
    private JTextField passwordTextField;
    private JLabel auotorisationNullPanel;
    private JButton autorisationButton;
    private JPanel autorisationPanel;
    private Server server;
    Client client;
    ServerInterface serverWindow;

    public ClientWindow(Server server){
        this.server = server;
        settingWindow();
        autorisationMenu(this);
        setVisible(true);
    }
    /**
     * Метод создания параметров окна
     */
    public void settingWindow() {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat Client");
        setLocation(WIDTH - 100, HEIGHT - 100);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    /**
     * Метод создания элементов для авторизации клиента
     */
    public void autorisationMenu(ClientInterface clientWindow){
        autorisationPanel = new JPanel(new GridLayout(3, 2));
        autorisationPanel.setBackground(Color.cyan);
        loginLabel = new JLabel("Логин: ");
        loginTextField = new JTextField();
        passwordLabel = new JLabel("Пароль");
        passwordTextField = new JTextField();
        auotorisationNullPanel = new JLabel();
        autorisationButton = new JButton("Войти");
        autorisationPanel.add(loginLabel);
        autorisationPanel.add(loginTextField);
        autorisationPanel.add(passwordLabel);
        autorisationPanel.add(passwordTextField);
        autorisationPanel.add(auotorisationNullPanel);
        autorisationPanel.add(autorisationButton);
        add(autorisationPanel, BorderLayout.NORTH);

        autorisationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(server.isServerStart() && !loginTextField.getText().equals("") && !passwordTextField.getText().equals("")) {
                    client = new Client(loginTextField.getText(), passwordTextField.getText(), clientWindow);
                    server.connectUser(client);
                    server.whriteInLogFile(client.getLoginClient() + " вошел в чат");
                    setTitle("Chat (" + client.getLoginClient() + ")");
                    autorisationPanel.setVisible(false);
                    chatWindow();
                }
                else {
                    auotorisationNullPanel.setText("Сервер выключен!");
                }
            }
        });
    }
    /**
     * Метод создания элементов чата
     */
    public void chatWindow() {
        messageField = new JTextField();
        sendMessage = new JButton("Отправить");
        messagePanel = new JPanel(new GridLayout(1, 2));
        messagePanel.add(messageField);
        messagePanel.add(sendMessage);
        add(messagePanel, BorderLayout.SOUTH);
        try {
            chat = new TextArea(server.readFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        add(chat);

        sendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.getClientList().contains(client) && !messageField.getText().equals("") && server.isServerStart()){
                    server.whriteInLogFile(client.getLoginClient() + ": " + messageField.getText());
                    messageField.setText("");
                    for (Client client1: server.getClientList())
                    {
                        client1.mailing();
                    }
                }
            }
        });
        messageField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (server.getClientList().contains(client) && !messageField.getText().equals("") && server.isServerStart()){
                    server.whriteInLogFile(client.getLoginClient() + ": " + messageField.getText());
                    messageField.setText("");
                    for (Client client1: server.getClientList())
                    {
                        client1.mailing();
                    }
                }
            }
        });
    }
    public Client getClient()
    {
        return client;
    }
    /**
     * Метод для обновления чата клиентов
     */
    public void updateChat(){
        chat.setText("");
        try {
            chat.setText(server.readFile());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
