package es.ubu.inf.edat.pr08;


public class RegistroEvaluacion {


	/**
	 * Metodo que permite incorporar una nueva nota de evaluacion
	 * al expediente de un alumno.
	 * 
	 * @param dni identificador del alumno
	 * @param nota nota obtenida en la prueba
	 * @return Expediente del alumno tras ser modificado.   
	 */
	public Alumno anadeNota(String dni, float nota){
		//TODO Implementar por el alumno		
	}

	/**
	 * Metodo que permite eliminar una nota de evaluacion del expediente de un alumno.
	 * En caso de que solo tuviera una evaluación y se elimine ésta, se deberá eliminar
	 * el expediente del alumno, para evitar confusiones.
	 * 
	 * @param dni identificador del alumno
	 * @param nota nota a eliminar del expediente.
	 * @return Expediente del alumno tras ser modificado.
	 * 		   En caso de que no estuviera incluido o de que al eliminar la nota, 
	 * 		   se necesite eliminar el alumno (porque quede a 0), devolverá null.  
	 */
	public Alumno eliminaNota(String dni, float nota){
		//TODO Implementar por el alumno
	}

	/**
	 * Metodo que permite consultar la nota de un alumno
	 * 
	 * @param dni identificador unico del alumno sobre el que se consulta
	 * @return nota media obtenida por el alumno
	 */
	public float devuelveNota(String dni){
		//TODO Implementar por el alumno
	}

	/**
	 * Metodo que permite obtener un listado de los expedientes de alumnos de
	 * la clase. Deberán aparecer ORDENADOS por su identificador (DNI).
	 *  
	 * @return Listado completo de todos los alumnos incluidos en la clase
	 */
	public List<Alumno> devuelveListado(){
		//TODO Implementar por el alumno
	}

	/**
	 * Metodo que permite obtener un listado de PARTE de los expedientes 
	 * de alumnos de la clase. Deberán aparecer ORDENADOS por su 
	 * identificador (DNI).
	 * Se obtendrá el expediente desde el primer alumno hasta aquel que se
	 * indica como parámetro (excluido).  
	 * 
	 * @param dniFin identificador a partir del cual se incluyen los expedientes 
	 *        en el listado
	 * @return Listado completo de todos los alumnos cuyo identificador sea 
	 * ANTERIOR al especificado como parametro.
	 */
	public List<Alumno> devuelveListadoHasta(String dniFin){
		//TODO Implementar por el alumno
	}

	/**
	 * Metodo que permite obtener un listado de PARTE de los expedientes 
	 * de alumnos de la clase. Deberán aparecer ORDENADOS por su 
	 * identificador (DNI).
	 * Se obtendrá el expediente desde el alumno que se indica como parámetro
	 * (incluido), hasta el último que aparezca en el listado.  
	 * 
	 * @param dniInicio identificador del primer expediente que aparecerá en el listado 
	 * @return Listado completo de todos los alumnos cuyo identificador sea 
	 * IGUAL o POSTERIOR al especificado como parametro.
	 */
	public List<Alumno> devuelveListadoDesde(String dniInicio){
		//TODO Implementar por el alumno
	}


	/**
	 * Metodo que permite obtener un listado de PARTE de los expedientes 
	 * de alumnos de la clase. Deberán aparecer ORDENADOS por su 
	 * identificador (DNI).
	 * Se obtendrá el expediente desde el alumno que se indica como parámetro
	 * de inicio (incluido), hasta aquel que se especificque como parametro de fin
	 * (excluido).  
	 * 
	 * @param dniInicio identificador del primer expediente que aparecerá en el listado 
	 * 
	 * @param dniInicio DNI del primer alumno que se incluirá en el listado
	 * @param dniFin DNI del alumno a partir del cual no se incluirán en el listado
	 * @return Listado completo de todos los alumnos cuyo identificador sea 
	 * IGUAL o POSTERIOR al especificado como primer parametro y ANTERIOR a aquel que se
	 * especifica como segundo parametro.
	 */
	public List<Alumno> devuelveListadoEntre(String dniInicio, String dniFin){
		//TODO Implementar por el alumno
	}

	/** 
	 * Metodo que permite obtener un listado de los expedientes de alumnos de
	 * la clase. Deberán aparecer ORDENADOS en función de la media de su expediente.
	 * 
	 * @return Listado completo de los alumnos del curso ordenado POR NOTA media del expediente.
	 */
	public List<Alumno> devuelveListadoNotas(){
		//TODO Implementar por el alumno
	}

	/**
	 * Metodo que permite recuperar los expedientes de un conjunto de alumnos que se facilita
	 * como parametro, siempre y cuando dichos expedientes estuvieran contenidos en el conjunto inicial.
	 * El conjunto resultante deberá ser una vista de la información almacenada. Al realizar esta 
	 * CONSULTA no se deberá modificar la coleccion que almacena los datos de los alumnos. 
	 * La modificación de los datos de un alumno en la vista si puede modificar los datos originales. 
	 * 
	 * @param dniIncluir conjunto de identificadores a incluir
	 * @return conjunto de alumnos cuyos identificadores corresponden a los facilitados como parámetro
	 *         (en caso de tener un expediente asociado)
	 */
	public Set <Alumno> filtraIncluyendoAlumnos (Set<String> dniIncluir){
		//TODO Implementar por el alumno
	}

	/**
	 * Metodo que permite recuperar los expedientes de aquellos alumnos que NO se encuentren entre los
	 * alumnos que se facilita como parametro.
	 * El conjunto resultante deberá ser una vista de la información almacenada. Al realizar esta 
	 * CONSULTA no se deberá modificar la coleccion que almacena los datos de los alumnos. 
	 * La modificación de los datos de un alumno en la vista si puede modificar los datos originales. 
	 * 
	 * @param dniExcluir conjunto de identificadores a excluir del conjunto de respuesta
	 * @return conjunto de alumnos cuyos identificadores corresponden a los facilitados como parámetro
	 *         (en caso de tener un expediente asociado)
	 */
	public Set <Alumno> filtraExcluyendoAlumnos (Set<String> dniExcluir){
		//TODO Implementar por el alumno
	}

	/**
	 * Devuelve el número de alumnos diferentes que se han evaluado hasta ahora. En caso de que
	 * un alumno no haya recibido evaluación aún, no se habrá incluido en el listado.  
	 *  
	 * @return Numero de alumnos evaluados
	 */
	public int numeroAlumnos(){
		//TODO Implementar por el alumno
	}

	/**
	 * Permite limpiar completamente el listado de alumnos.
	 */
	public void limpiaListado(){
		//TODO Implementar por el alumno
	}

} 