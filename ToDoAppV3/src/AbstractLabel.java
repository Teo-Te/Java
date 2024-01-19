import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public abstract class AbstractLabel extends JLabel {
    private static final Font font = new Font("Serif", Font.PLAIN, 25);
    private static final Color fgColor = new Color(0, 0, 0);
    private static final Color shadowColor = new Color(0, 0, 0, 50);
    private boolean shadow = true;

    public AbstractLabel(String text) {
        super(text);
        customizeLabel();
    }

    private void customizeLabel() {
        setFont(font);
        setForeground(fgColor);
    }

    public void setShadow(boolean shadow){
        this.shadow = shadow;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw drop shadow
        if (shadow){
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setColor(shadowColor);
        g2d.setFont(getFont().deriveFont(Font.BOLD));

        int textWidth = g2d.getFontMetrics().stringWidth(getText());
        int shadowX = (getWidth() - textWidth) / 2;

        int textHeight = g2d.getFontMetrics().getHeight();
        int shadowY = (getHeight() - textHeight) / 2 + textHeight - 2;

        g2d.drawString(getText(), shadowX, shadowY);
        g2d.dispose();
        }
    }
}
