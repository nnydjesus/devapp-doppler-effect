package ar.edu.unq.dopplereffect.presentation.theme;

import java.util.ArrayList;
import java.util.List;

public class Theme {

    public static final List<UITheme> THEMES = new ArrayList<UITheme>();

    static {
        THEMES.add(RedmondTheme.getInstance());
        THEMES.add(LeFrogTheme.getInstance());
        // themes.add(MintChocTheme.getInstance());
        // themes.add(UILightnessTheme.getInstance());
        THEMES.add(CupertinoTheme.getInstance());
    }

    private Theme() {
        super();
    }
}
