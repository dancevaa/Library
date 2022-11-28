package dto;

public enum Action {
    READ("read"),
    ADD("add"),
    DELETE("delete");

    private final String actionName;

    Action(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }

    public static Action findAction(String action) {
        return Action.valueOf(action);
    }
}
