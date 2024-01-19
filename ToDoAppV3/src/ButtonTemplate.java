import java.awt.event.ActionListener;

public class ButtonTemplate extends AbstractButton {
    public ButtonTemplate(String text) {
        super(text);
    }

    @Override
    public void onClick(ActionListener actionListener) {
        addActionListener(actionListener);
    }
}
