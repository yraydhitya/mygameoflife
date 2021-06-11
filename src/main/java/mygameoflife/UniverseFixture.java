package mygameoflife;

import java.util.Set;

public class UniverseFixture {

    public static Universe block() {
        return Universe.of(Set.of(
                Cell.ofDead(0, 0),
                Cell.ofLive(1, 1), Cell.ofLive(1, 2),
                Cell.ofLive(2, 1), Cell.ofLive(2, 2),
                Cell.ofDead(3, 3)
            ));
    }

    public static Universe beeHive() {
        return Universe.of(Set.of(
                Cell.ofDead(0, 0),
                Cell.ofLive(1, 2), Cell.ofLive(1, 3),
                Cell.ofLive(2, 1), Cell.ofLive(2, 4),
                Cell.ofLive(3, 2), Cell.ofLive(3, 3),
                Cell.ofDead(4, 5)
            ));
    }

    public static Universe blinker1() {
        return Universe.of(Set.of(
                Cell.ofDead(0, 0),
                Cell.ofLive(2, 1), Cell.ofLive(2, 2), Cell.ofLive(2, 3),
                Cell.ofDead(4, 4)
            ));
    }

    public static Universe blinker2() {
        return Universe.of(Set.of(
                Cell.ofDead(0, 0),
                Cell.ofLive(1, 2), Cell.ofLive(2, 2), Cell.ofLive(3, 2),
                Cell.ofDead(4, 4)
            ));
    }
}
