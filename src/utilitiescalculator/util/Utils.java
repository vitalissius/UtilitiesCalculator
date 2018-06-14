package utilitiescalculator.util;

import java.awt.Component;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.JOptionPane;

public class Utils {
    public static void showExceptionDialog(Component component, Exception exception, String title) {
        String message = "";
        try (StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter)) {

            exception.printStackTrace(printWriter);
            message = stringWriter.toString();

        } catch (IOException ignored) {
        }
        JOptionPane.showMessageDialog(component, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
