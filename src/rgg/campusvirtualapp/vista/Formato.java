package rgg.campusvirtualapp.vista;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Formato {
	public static void cambiarTypeface(final Context context, final View v,
			final Object tipo) {
		try {
			if (v instanceof ViewGroup) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0; i < vg.getChildCount(); i++) {
					View child = vg.getChildAt(i);
					cambiarTypeface(context, child, tipo);
				}
			} else if (v instanceof TextView) {
				if (((TextView) v).getTypeface().getStyle() == Typeface.BOLD) {
					((TextView) v).setTypeface((Typeface) tipo, Typeface.BOLD);
				} else
					((TextView) v).setTypeface((Typeface) tipo);
			} else if (v instanceof EditText) {
				if (((EditText) v).getTypeface().getStyle() == Typeface.BOLD) {
					((EditText) v).setTypeface((Typeface) tipo, Typeface.BOLD);
				} else
					((EditText) v).setTypeface((Typeface) tipo);

			} else if (v instanceof Button) {
				if (((Button) v).getTypeface().getStyle() == Typeface.BOLD) {
					((Button) v).setTypeface((Typeface) tipo, Typeface.BOLD);
				} else
					((Button) v).setTypeface((Typeface) tipo);
				((Button) v).setTypeface((Typeface) tipo);
			}
		} catch (Exception e) {
		}
	}
}
