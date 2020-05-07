package net.sierrainfosys.pmo.dto;

/**
 * Created by anupamchugh on 22/12/17.
 */

public class MenuModel {

    public String menuName, url;
    public boolean hasChildren, isGroup;

    public MenuModel(String menuName, boolean isGroup, boolean hasChildren) {

        this.menuName = menuName;
        this.isGroup = isGroup;
        this.hasChildren = hasChildren;
    }
}
