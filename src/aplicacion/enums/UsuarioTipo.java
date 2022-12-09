package aplicacion.enums;

public enum UsuarioTipo {
	Admin(0),
	Paciente(1),
	Medico(2);
	
	private int tipo; 

	private UsuarioTipo(int tipo) { 
        this.tipo = tipo; 
    }  

    public int getTipo() { 
        return this.tipo; 
    }

    public static UsuarioTipo fromId(int tipo) {
        for (UsuarioTipo type : values()) {
            if (type.getTipo() == tipo) {
                return type;
            }
        }
        return null;
    }
}