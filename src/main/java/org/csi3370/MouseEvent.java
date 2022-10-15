package org.csi3370;

public class MouseEvent {

    int mouseX;
    int mouseY;
    EventType type;

    public static enum EventType {
        CLICK,
        DRAG,
        RELEASE
    }

    public MouseEvent(EventType event, int mouseX, int mouseY) {
        type = event;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }
}
