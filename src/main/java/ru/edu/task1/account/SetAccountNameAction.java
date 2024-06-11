package ru.edu.task1.account;

import ru.edu.task1.Modifable;

public class SetAccountNameAction implements Modifable {
    private Account account;
    private String clientName;
    private String prevClientName;

    public SetAccountNameAction(Account account, String clientName) {
        this.account = account;
        this.clientName = clientName;
    }

    @Override
    public void modify() {
        prevClientName = account.getClientName();
        account.setClientName(clientName);
    }

    @Override
    public void undo() {
        account.setClientName(prevClientName);
    }
}
