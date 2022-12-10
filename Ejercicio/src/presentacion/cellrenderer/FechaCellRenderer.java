package presentacion.cellrenderer;

import java.text.SimpleDateFormat;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class FechaCellRenderer extends DefaultTableCellRenderer {
    public FechaCellRenderer() {
    	super();
    	this.setHorizontalAlignment(JLabel.RIGHT);
    }

    @Override
    public void setValue(Object value) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        setText((value == null) ? "" : simpleDateFormat.format(value));
    }
}
