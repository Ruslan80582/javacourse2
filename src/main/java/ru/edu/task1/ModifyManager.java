package ru.edu.task1;

import ru.edu.task1.exceptions.UndoException;

import java.util.Stack;

public class ModifyManager {
    private final Stack<Modifable> actionStack = new Stack<>();

    public void modify(Modifable action) {
        action.modify();
        actionStack.push(action);
    }

    public void undo() throws UndoException {
        if (actionStack.isEmpty()) throw new UndoException("Can't undo");
        Modifable action = actionStack.pop();
        action.undo();
    }
}
