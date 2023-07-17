package ru.job4j.tracker;

import java.util.Arrays;
import java.util.List;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Store tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
           run = actions.get(select).execute(input, tracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu:");
        for (int i = 0; i < actions.size(); i++) {
            out.println(i + ". " + actions.get(i).name());
        }
    }

    public static void main(String[] args) {
        Output out = new ConsoleOutput();
        Input input = new ValidateInput(out, new ConsoleInput());
        try (Store tracker = new MemTracker()) {
            List<UserAction> actions = Arrays.asList(
                    new CreateAction(out),
                    new FindAllActions(out),
                    new ReplaceAction(out),
                    new DeleteAction(out),
                    new FindActionByName(out),
                    new FindActionById(out),
                    new ExitProgram(out),
                    new CreateManyItems(out),
                    new DeleteAllItems(out)
            );
            new StartUI(out).init(input, tracker, actions);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
