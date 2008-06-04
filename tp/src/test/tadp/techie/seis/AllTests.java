package tadp.techie.seis;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AlumnoTest.class,
        CriterioTest.class,        
        ExamenCorregidoBuilderTest.class,
        ExamenTest.class,
        MateriaTest.class,
        PreguntaTest.class
        })
public class AllTests {

}
