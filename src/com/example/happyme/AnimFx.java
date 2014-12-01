package com.example.happyme;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class AnimFx {
	public static void slide_down(Context ctx, View v) {

		Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slidedown);
		if (a != null) {
			a.reset();
			if (v != null) {
				v.clearAnimation();
				v.startAnimation(a);
			}
		}
	}

	public static void slide_up(Context ctx, View v) {

		Animation a = AnimationUtils.loadAnimation(ctx, R.anim.slideup);
		if (a != null) {
			a.reset();
			if (v != null) {
				v.clearAnimation();
				v.startAnimation(a);
			}
		}
	}

}