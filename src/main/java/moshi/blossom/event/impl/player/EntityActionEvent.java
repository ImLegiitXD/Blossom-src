package moshi.blossom.event.impl.player;

import moshi.blossom.event.Event;

public class EntityActionEvent extends Event {

    private boolean sprinting;

    private boolean sneaking;

    public EntityActionEvent(boolean sprinting, boolean sneaking) {
        this.sprinting = sprinting;

        this.sneaking = sneaking;

    }

    public boolean isSprinting() {
        return this.sprinting;

    }

    public void setSprinting(boolean sprinting) {
        this.sprinting = sprinting;

    }

    public boolean isSneaking() {
        return this.sneaking;

    }

    public void setSneaking(boolean sneaking) {
        this.sneaking = sneaking;

    }

}
