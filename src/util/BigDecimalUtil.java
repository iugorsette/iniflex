package util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class BigDecimalUtil {

    private static final DecimalFormat decimalFormat;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        decimalFormat = new DecimalFormat("#,##0.00", symbols);
    }

    public static String format(BigDecimal value) {
        return decimalFormat.format(value);
    }
}
