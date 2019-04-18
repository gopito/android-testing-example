package ru.android2019.utils;

import android.view.View;

import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.annotation.RealObject;
import org.robolectric.shadows.ShadowView;

@Implements(View.class)
public class MyShadowView extends ShadowView {
    @RealObject
    private View realView;




    @Implementation
    public static boolean clickOn(View view){
        dump(view);
        return ShadowView.clickOn(view);
    }
}
