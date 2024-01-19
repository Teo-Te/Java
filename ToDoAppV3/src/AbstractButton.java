import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Color;

public abstract class AbstractButton extends JButton {
    private static final Font font = new Font("Serif", Font.PLAIN, 20);
    private static final Color bgColor = new Color(40, 40, 43);
    private static final Color fgColor = new Color(255, 255, 240);
    private static final Color hoverColor = new Color(80, 80, 80);

    public AbstractButton(String text) {
        super(text);
        customizeButton();
    }

    private void customizeButton() {
        setFont(font);
        setBackground(bgColor);
        setForeground(fgColor);
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        setFocusPainted(false);
        setOpaque(true);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(hoverColor);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(bgColor);
            }
        });
    }

    public abstract void onClick(ActionListener actionListener);
}
