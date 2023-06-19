package runner;

import br.pe.fj.rest.Products;
import br.pe.fj.rest.Users;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Products.class,
        Users.class,
})
public class Runner     {
}
