package utilitiescalculator;

import java.util.Map;
import java.util.EnumMap;

import java.awt.Font;

public enum Resizer {
    INSTANCE;
    public enum FontSize {
        ELEVEN(11),
        THIRTEEN(13),
        FIVETEEN(15);
        
        private final int size;

        private FontSize(int size) {
            this.size = size;
        }
    }
    
    private static final StringBuilder text = new StringBuilder();
    private static FontSize fontSize = FontSize.ELEVEN;
    
    private static final Map<FontSize, Font> map = new EnumMap<>(FontSize.class);
    static {
        for (FontSize fs : FontSize.values()) {
            map.put(fs, new Font("Microsoft Sans Serif", java.awt.Font.PLAIN, fs.size));
        }
    }
    
    public static Resizer getInstance(String text, FontSize fontSize) {
        Resizer.text.append(text).append("(").append(String.valueOf(fontSize.size)).append(")");
        Resizer.fontSize = fontSize;
        return INSTANCE;
    }

    public static Resizer getInstance() {
        return getInstance("", FontSize.ELEVEN);
    }

    public void shiftToNextFontSize() {
        FontSize[] values = FontSize.values();
        int next = (fontSize.ordinal() + 1) % values.length;
        fontSize = FontSize.values()[next];        
    }

    public String getText() {
        return text.toString();
    }

    private void apply(java.awt.Component component) {
        if (component instanceof javax.swing.JPanel) {
            javax.swing.border.Border border = ((javax.swing.JPanel) component).getBorder();
            if (border instanceof javax.swing.border.TitledBorder) {
                ((javax.swing.border.TitledBorder) border).setTitleFont(map.get(fontSize));
            }
        }
        else {
            component.setFont(map.get(fontSize));
        }
        updateText();
    }

    private void updateText() {
        int charAt = text.indexOf("(");
        if (charAt != -1) {
            text.replace(charAt + 1, charAt + 1 + 2, Integer.toString(fontSize.size));
        }
    }

    public void applyTo(java.util.List<java.awt.Component> components) {
        components.forEach((component) -> {
            apply(component);
        });
    }

    @Override
    public String toString() {
        return fontSize.toString();
    }
}
