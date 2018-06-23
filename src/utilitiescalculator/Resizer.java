package utilitiescalculator;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public enum Resizer {
    INSTANCE;

    public enum FontSize {
        ELEVEN(11),
        TWELVE(12),
        THIRTEEN(13),
        FOURTEEN(14),
        FIFTEEN(15);

        private final int sizeCode;

        FontSize(int sizeCode) {
            this.sizeCode = sizeCode;
        }

        public int getSizeCode() {
            return sizeCode;
        }
    }

    private static final StringBuilder text = new StringBuilder();

    private static final Map<FontSize, Font> map = new EnumMap<>(FontSize.class);

    static {
        for (FontSize fs : FontSize.values()) {
            map.put(fs, new Font("Tahoma", Font.PLAIN, fs.sizeCode));
        }
    }

    private static FontSize fontSize;

    static {
        final FontSize fntSize = FontSize.ELEVEN;
        Resizer.fontSize = fntSize;

        final String interfix = String.valueOf(fntSize.sizeCode);
        final String prefix = Dictionary.INSTANCE.getWord(Dictionary.Keyword.BT_FONT);
        text.append(prefix).append("(").append(interfix).append(")");
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

    @Override
    public String toString() {
        updateTextByLanguage();
        return text.toString();
    }

    private void updateTextBySize() {
        int charAt = text.indexOf("(");
        if (charAt != -1) {
            text.replace(charAt + 1, charAt + 1 + 2, Integer.toString(fontSize.sizeCode));
        }
    }

    private void apply(Component component) {
        if (component instanceof JPanel) {
            Border border = ((JPanel) component).getBorder();
            if (border instanceof TitledBorder) {
                ((TitledBorder) border).setTitleFont(map.get(fontSize));
            }
        } else {
            if (component instanceof JTable) {
                ((JTable) component).setRowHeight(fontSize.getSizeCode() + 5);
            }
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
