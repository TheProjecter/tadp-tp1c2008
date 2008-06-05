package tadp.techie.seis;

import java.util.Set;

import tadp.techie.seis.ItemExamen.TiposItem;

public class PrototipoItem<T extends ItemExamen>
{
	private TiposItem tipo;
	private String unidadTemantica;
	private int complejidad;
	
	private Class<T> subClase;
	/**
	 * Instancia un nuevo prototipo.
	 * Hay que indicarle que implementacion de ItemExamen tiene que usar.
	 * Hay que hacerlo dos veces por el generico y pasandole la clase por
	 * el constructor.
	 * FIXME
	 * La clase que se le indica no puede ser abstracta (para las preguntas pasarle una
	 * que extienda Pregunta, pero lo va a tomar como si fuera pregunta
	 * @param subClase
	 */
	public PrototipoItem(Class<T> subClase)
	{
		setTipo(null);
		setUnidadTemantica(null);
		setComplejidad(0);
		this.subClase = subClase;
	}
	/**
	 * Este metodo no sirve, le dejo pero hay que borrarlo
	 * Dado una una implementacion de ItemExamen (p.e Pregunta o Ejercicio)
	 * especificado al instanciar el Prototipo este metodo devuelve la
	 * coleccion de esos items que contiene la materia.
	 * Para eso instancia uno de esos items y le pide la coleccion
	 * (este item sabe que le tiene que pedir a la materia)
	 * 
	 * Lo tuve que hacer con reflection porque no pude usar el generic
	 * para instanciarlo (era la idea original)
	 * 
	 * @param materia la materia que contiene los items
	 * @return el set con los items de este tipo
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public Set<T> getItemsSetFrom0(Materia materia)
	{
		ItemExamen item;
		try {
			item = subClase.getConstructor().newInstance();
			return (Set<T>)item.getItemsFrom(materia);
		} catch(Exception e) {
			// TODO
			return null;
		}
		
	}
	
	/**
	 * Devuelve si un determinado item cumple con los requisitos de este prototipo
	 * Esto es, si es de la subClase especificada al momento de creacion y ademas
	 * los campos especificados son iguales. Los campos no asignados o que se les
	 * asigno null o 0 los ignora
	 * @param item un item para comprar
	 * @return si se parece al prototipo
	 */
	public boolean itemSeParece(ItemExamen item)
	{
		return 		subClase.isInstance(item)
				&&	(this.getTipo() == null || item.getTipo().equals(this.getTipo()))
				&&	(this.getUnidadTemantica() == null || item.getUnidadTematica().equals(this.getTipo())
				&&	(this.getComplejidad() == 0) || item.getComplejidad() == this.getComplejidad());
	}

	public void setTipo(TiposItem tipo) {
		this.tipo = tipo;
	}

	public TiposItem getTipo() {
		return tipo;
	}

	public void setUnidadTemantica(String unidadTemantica) {
		this.unidadTemantica = unidadTemantica;
	}

	public String getUnidadTemantica() {
		return unidadTemantica;
	}

	public void setComplejidad(int complejidad) {
		this.complejidad = complejidad;
	}

	public int getComplejidad() {
		return complejidad;
	}

}
