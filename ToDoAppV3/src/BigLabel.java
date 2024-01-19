import java.awt.Font;

import javax.swing.SwingConstants;

public class BigLabel extends AbstractLabel {
    public BigLabel(String text) {
        super(text);
        customizeBigLabel();
    }

    private void customizeBigLabel() {
        setFont(new Font("Arial", Font.BOLD, 30));
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
