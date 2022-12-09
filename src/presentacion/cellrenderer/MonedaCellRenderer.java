package presentacion.cellrenderer;

import java.text.NumberFormat;

import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

public class MonedaCellRenderer extends DefaultTableCellRenderer {

	public MonedaCellRenderer() {
		super();
		this.setHorizontalAlignment(JLabel.RIGHT);
	}

	@Override
	public void setValue(Object value) {
		Number number = (Number) value;
		if ((value != null) && (value instanceof Number)) {
			NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
			value = numberFormat.format(number.doubleValue());
		}
		super.setValue(value);
	}
}