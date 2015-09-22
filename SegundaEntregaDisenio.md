## Puntos Elegidos ##
Elegimos los puntos 1, 4 y 5

## Punto 5 ##


Siguiendo lo que planteamos para el primer punto decidimos hacer que el Buider sea el que corrija el examen.

Entonces al momento de instanciar el Buider se la asigna un Examen y un Alumno, despues se le agregan n Criterios mediante el metodo addCriterio().
Ahora necesitabamos una manera de relacionar una pregunta con una nota (B|R|M|etc). (La nota de los puntos esta mal llamada RespuestaPregunta en el codigo)

Primera opcion fue hacer un Mapa y pasarselo al builder, pero esto estaria de lado del cliente y queda feo.

Segunda opcion fue, hacer que el map este en el buider y que haya que indicarle para cada pregunta la nota con un metodo que se llama varias veces setNota(Pregunta, Nota), pero tampoco era lo mejor, porque que pasaria si falta decir la nota de alguna, y ademas tenemos que preguntar si cumple el criterio para ponerle la nota o no. Asi que pensamos una manera mas frameworkish.

Para esto el cliente tiene que implementar un Corrector y darselo al buider, el buider cuando se le indica que corrija el examen le pregunta a Corrector por la nota de cada Pregunta y despues de obtener todas las notas le pregunta a sus criterios si el examen esta para aprobar, y solo si lo esta le pregunta al Corrector la nota.

Entonces esta Interfaz tiene dos metodos. Uno que te pasa una pregunta y vos le decis bien, regular, mal, etc. Y otro que te pregunta la nota, el Buider lo llama unicamente si el examen cumple las condiciones.

Seria mas o menos asi:

#Cliente implements Corrector
```
{
	b = new ExamenCorregidoBuider(alumno, examen, this);

	b.addCriterio(c1);
	b.addCriterio(c2);

	ExamenCorregido ex = b.corregir();
}

//RespuestaPregunta es la nota (B|M|R|etc)
RespuestaPregunta getNotaItem(ItemExamen item)
{
	//...
	return BIEN;
}

int getNotaFinal()
{
	//...
	return 4;
}
```