package ee.taltech.iti0202.delivery;

import java.util.List;

public class DummyStrategy implements Strategy {
    private List<Action> actions;

    public DummyStrategy(List<Action> actions) {
        this.actions = actions;
    }

    @Override
    public Action getAction() {
        return actions.remove(0);
    }
}
