package client.Regic.src;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedBorder extends AbstractBorder {
    private int borderRadius;

    public RoundedBorder(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(x, y, width - 1, height - 1, borderRadius, borderRadius);
        g2.setColor(c.getForeground());
        g2.draw(roundedRectangle);

        g2.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(borderRadius, borderRadius, borderRadius, borderRadius);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.top = borderRadius;
        insets.left = borderRadius;
        insets.bottom = borderRadius;
        insets.right = borderRadius;
        return insets;
    }
}
