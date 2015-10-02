import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.Test;
import org.rakam.Recipe;

import java.io.IOException;
import java.io.InputStream;


public class RecipeConfigTest {
    @Test
    public void test() throws IOException {
        InputStream io = getClass().getResourceAsStream("recipes/ecommerce_test.yml");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Recipe recipe = mapper.readValue(io, Recipe.class);

    }
}
