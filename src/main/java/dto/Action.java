package dto;

public enum Action {
    READ("read"),
    ADD("add"),
    DELETE("delete"),
    EXIT("exit");

    private final String actionName;

    Action(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    public static Action findAction(final String action) {
        return Action.valueOf(action.toUpperCase());
    }
}
