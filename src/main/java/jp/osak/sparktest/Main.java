package jp.osak.sparktest;

import net.logstash.log4j.JSONEventLayoutV1;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("jsonlogger");
        logger.info("test");

        SparkSession spark = SparkSession.builder()
                .appName("EmrLog4JTest")
                .getOrCreate();
        List<Integer> data = Arrays.asList(1, 2, 3);
        Dataset<Integer> dataset = spark.createDataset(data, Encoders.INT());
        Dataset<Integer> mapped = dataset.map(
                Main::mapper,
                Encoders.INT()
        );
        List<Integer> result = mapped.collectAsList();
        for (Integer val : result) {
            System.out.println(val);
            logger.info(val);
        }
        spark.stop();
    }

    private static int mapper(int i) throws IOException {
        Logger logger = LogManager.getLogger("mapperlogger");
        String logDir = System.getProperty("spark.yarn.app.container.log.dir");
        logger.addAppender(new FileAppender(new JSONEventLayoutV1(), logDir + "/" + "application.log.json"));
        System.out.println("This is sout in mapper");
        System.out.println(logDir);
        logger.info("Get " + i);
        return i * 10;
    }
}
