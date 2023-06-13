package client.app.src;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class RoundedCornerButtonUI extends BasicButtonUI {
    private int arcWidth;
    private int arcHeight;

    public RoundedCornerButtonUI(int arcWidth, int arcHeight) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
    }

    @Override
    protected void installDefaults(AbstractButton button) {
        super.installDefaults(button);
        button.setOpaque(false);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        paintBackground(g, button);
        super.paint(g, button);
    }

    private void paintBackground(Graphics g, AbstractButton button) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = button.getWidth();
        int height = button.getHeight();
        int yOffset = button.getModel().isPressed() ? 1 : 0;

        g2d.setColor(button.getBackground());
        g2d.fillRoundRect(0, yOffset, width - 1, height - yOffset - 1, arcWidth, arcHeight);

        g2d.dispose();
    }
}
