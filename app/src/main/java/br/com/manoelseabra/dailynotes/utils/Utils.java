package br.com.manoelseabra.dailynotes.utils;

import android.graphics.Color;

public class Utils {
    private static int[] colors = {
            Color.WHITE,
            Color.parseColor("#bae7c0"),
            Color.parseColor("#f8c7c7"),
            Color.parseColor("#c8d6fb"),
            Color.parseColor("#fef6a4"),
            Color.parseColor("#fbdab2"),
    };

    public static int getNextColor(int color) {
        for(int i = 0; i < colors.length; i++) {
            if(colors[i] == color) {
                if(i == colors.length - 1)
                    return colors[0];
                return colors[i + 1];
            }
        }
        return colors[0];
    }
}
