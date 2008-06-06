package tadp.techie.seis;

import java.util.Set;

import tadp.techie.seis.ItemExamen.TiposItem;
/**
 * Representa un modelo de {@link ItemExamen} por el cual se pueden seleccionar
 * items reales que se parezcan a este prototipo
 * El prototipo tiene un tipo generico que es una clase que extiende a ItemExamen
 * Puede ser Ejercicio, Pregunta, ADesarrollar, Choice o cualquier otra.
 * Cuando se instancia el prototipo el contructor recibe una clase que tiene que
 * ser la mismo del generic
 * <p>
 * El prototipo dira si un item cumple con los requisitos para parecerce con el metodo
 * itemSeParece(). Para esto compara el item con los datos que tenga cargados.
 * Si un dato no se cargo se omite y se asume que cumple y se usaran los otros datos.
 * Para que se parezca tiene que complir con todos los que se hayan cargado.
 * <p>
 * El PrototipoItem es usado por {@link ExamenBuilder} para seleccionar los items
 * @author xuan
 *
 * @param <T> La clase que extiende a ItemPregunta a la cual tienen que parecerce los items
 * @see {@link ItemExamen}, {@link ExamenBuilder}
 */
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
	 * @param subClase La misma clase que se uso en el generic
	 */
	public PrototipoItem(Class<T> subClase)
	{
		setTipo(null);
		setUnidadTemantica(null);
		setComplejidad(0);
		this.subClase = subClase;
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
