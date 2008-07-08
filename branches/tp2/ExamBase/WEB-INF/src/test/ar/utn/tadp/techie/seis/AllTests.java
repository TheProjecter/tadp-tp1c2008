package test.ar.utn.tadp.techie.seis;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AlumnoTest.class,
        CriterioTest.class,        
        ExamenCorregidoBuilderTest.class,
        ExamenTest.class,
        MateriaTest.class,
        PreguntaTest.class,
        SerializacionXStreamTest.class
		})
public class AllTests {

}
