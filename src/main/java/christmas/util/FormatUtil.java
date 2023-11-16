package christmas.util;

import java.text.DecimalFormat;

public class FormatUtil {
    public static String toWonFormat(int origin) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(origin);
    }
}
