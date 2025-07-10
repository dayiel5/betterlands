package dayielyst.betterlands.util;

import javax.annotation.Nullable;

public enum TerracottaColors {
    // Values of all 17 known terracotta colors (includes uncolored "plain" variant.)
    PLAIN(0, null),
    WHITE(1, "white"),
    ORANGE(2, "orange"),
    MAGENTA(3, "magenta"),
    LIGHT_BLUE(4, "light_blue"),
    YELLOW(5, "yellow"),
    LIME(6, "lime"),
    PINK(7, "pink"),
    GRAY(8, "gray"),
    LIGHT_GRAY(9, "light_gray"),
    CYAN(10, "cyan"),
    PURPLE(11, "purple"),
    BLUE(12, "blue"),
    BROWN(13, "brown"),
    GREEN(14, "green"),
    RED(15, "red"),
    BLACK(16, "black");

    private final int index;
    private final String colorValue;

    TerracottaColors(int index, @Nullable String colorValue)
    {
        this.index = index;
        this.colorValue = colorValue;
    }

    public int getIndex() {return index;}
    public String getColorValue() {return colorValue;}

    public static TerracottaColors getColor(int index)
    {
        for(TerracottaColors color : TerracottaColors.values())
        {
            if(color.getIndex() == index) return color;
        }
        throw new IllegalArgumentException("No terracotta color with index " + index + "!");
    }
}