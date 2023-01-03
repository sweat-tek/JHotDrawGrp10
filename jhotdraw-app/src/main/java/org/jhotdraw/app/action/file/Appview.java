package org.jhotdraw.app.action.file;

import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.View;

public class Appview {
    View localview;
    View localemptyview;
    Application localapp;
    Boolean localbool;

    public View getLocalview() {
        return localview;
    }

    public void setLocalview(View localview) {
        this.localview = localview;
    }

    public View getLocalemptyview() {
        return localemptyview;
    }

    public void setLocalemptyview(View localemptyview) {
        this.localemptyview = localemptyview;
    }

    public Application getLocalapp() {
        return localapp;
    }

    public void setLocalapp(Application localapp) {
        this.localapp = localapp;
    }

    public Boolean getLocalbool() {
        return localbool;
    }

    public void setLocalbool(Boolean localbool) {
        this.localbool = localbool;
    }

    public Appview(View view, View emptyview, Application app, Boolean bool){
        localview = view;
        localemptyview = emptyview;
        localapp = app;
        localbool = bool;
    }
}
