package client.app.src;
import javax.swing.*;
import java.awt.*;

public class ComponentSizeSetter {
    public static void setFixedSize(JComponent component, int width, int height) {
        component.setPreferredSize(new Dimension(width, height));
        component.setMaximumSize(new Dimension(width, height));
        component.setMinimumSize(new Dimension(width, height));
    }
}
