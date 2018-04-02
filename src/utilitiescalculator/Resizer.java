package utilitiescalculator;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public enum Resizer {
    INSTANCE;

    public enum FontSize {
        ELEVEN(11),
        THIRTEEN(13),
        FIFTEEN(15);

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
            map.put(fs, new Font("Tahoma", Font.PLAIN, fs.size));
        }
    }

    public static Resizer getInstance(FontSize fontSize) {
        String prefix = Dictionary.INSTANCE.getWord(Dictionary.Keyword.BT_FONT);
        text.append(prefix).append("(").append(String.valueOf(fontSize.size)).append(")");
        Resizer.fontSize = fontSize;
        return INSTANCE;
    }

    public void shiftToNextFontSize() {
        FontSize[] fontSizes = FontSize.values();
        int next = (fontSize.ordinal() + 1) % fontSizes.length;
        fontSize = fontSizes[next];
    }

    private void updateTextByLanguage() {
        int charAt = text.indexOf("(");
        if (charAt != -1) {
            text.replace(0, charAt, Dictionary.INSTANCE.getWord(Dictionary.Keyword.BT_FONT));
        }
    }

    public String getText() {
        updateTextByLanguage();
        return text.toString();
    }

    private void updateTextBySize() {
        int charAt = text.indexOf("(");
        if (charAt != -1) {
            text.replace(charAt + 1, charAt + 1 + 2, Integer.toString(fontSize.size));
        }
    }

    private void apply(Component component) {
        if (component instanceof JPanel) {
            Border border = ((JPanel) component).getBorder();
            if (border instanceof TitledBorder) {
                ((TitledBorder) border).setTitleFont(map.get(fontSize));
            }
        }
        else {
            component.setFont(map.get(fontSize));
        }
        updateTextBySize();
    }

    public void applyTo(List<Component> components) {
        components.forEach(this::apply);
    }

    public FontSize getFontSize() {
        return fontSize;
    }

    public void setFontSize(FontSize fontSize) {
        Resizer.fontSize = fontSize;
    }
}
