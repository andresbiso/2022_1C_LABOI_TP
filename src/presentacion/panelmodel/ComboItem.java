package presentacion.panelmodel;

public class ComboItem<T> {
	private String label;
	private T value;

	public ComboItem(String label, T value) 
	{
		this.label = label;
		this.value = value;
	}
	
	public String getLabel() {
		return label;
	}
	
	public T getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return label;
	}
}
