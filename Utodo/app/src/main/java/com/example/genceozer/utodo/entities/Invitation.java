package com.example.genceozer.utodo.entities;

/**
 * Created by genceozer on 15/05/16.
 */
public class Invitation {

    private String groupTitle;
    private String invitorName;

    public Invitation() {
    }

    public Invitation(String groupTitle, String invitorName) {

        this.groupTitle = groupTitle;
        this.invitorName = invitorName;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getInvitorName() {
        return invitorName;
    }

    public void setInvitorName(String invitorName) {
        this.invitorName = invitorName;
    }
}
