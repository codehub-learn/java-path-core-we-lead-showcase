import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class DatabaseConnection {
    private static final String LINE_DELIMITER = "---------------------------------------";
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);
    private static final Lorem generator = LoremIpsum.getInstance();
    private static final String DB_CONNECTION_URL_MEMORY_MODE = "jdbc:h2:mem:sample";
    private static final Properties sqlCommands = new Properties();

    public static void main(String[] args) {

    }
}
