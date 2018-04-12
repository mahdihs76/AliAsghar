package nooran;

import static nooran.Config.CHILD2_VALUE;

/**
 * Created by mahdihs76 on 4/8/18.
 */
public class Child2 implements MyInterface {
    @Override
    public int field() {
        return CHILD2_VALUE;
    }
}
