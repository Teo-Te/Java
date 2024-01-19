import java.awt.Font;

public class SmallLabel extends AbstractLabel {
    public SmallLabel(String text) {
        super(text);
        customizeSmallLabel();
        setShadow(false);
    }

    private void customizeSmallLabel() {
        setFont(new Font("Serif", Font.PLAIN, 20));
    }
}
