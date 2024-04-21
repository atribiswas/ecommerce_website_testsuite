package demo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

public class LogConfig {

    private static final Logger logger = LogManager.getLogger(LogConfig.class);
    private static Boolean configured = false;

    public static void setUp() throws IOException {
        if(configured) return;

        String logFilePath = System.getProperty("user.dir")+"/src/test/resources/log4j-prop.xml";
        File configFile = new File(logFilePath);

        ConfigurationSource source = new ConfigurationSource(new FileInputStream(configFile));
        ConfigurationFactory factory = new XmlConfigurationFactory();
        Configuration configuration = factory.getConfiguration(null, source);
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.start(configuration);
        configured = true;
        
    }

    public static void tearDown() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        context.stop();
    }

    public static Logger getLogger(){
        return logger;
    }
}
