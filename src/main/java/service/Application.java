package service;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.StringUtils;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
public class Application {
    public static List<String> fillerWordsFrench;
    public static List<String> fillerWordsEnglish;
    public static List<String> stopWordsFrench;
    public static List<String> stopWordsFrench2;
    public static List<String> stopWordsEnglish;
    public static StanfordCoreNLP frenchPOSpipeline;
    public static StanfordCoreNLP enPOSpipeline;
    public static WordVectors enWordVectors;
    public static WordVectors frWordVectors;

    public static void main(String[] args) throws IOException {
        Settings.init();
        System.out.println("loading static resources");
        loadResources();
        SpringApplication.run(Application.class, args);
    }

    public static void loadResources() {
        //Properties props = PropertiesUtils.asProperties("props", "StanfordCoreNLP-french.properties");
        Properties frenchProps = StringUtils.argsToProperties(
                new String[]{"-props", "StanfordCoreNLP-french.properties"});
        frenchProps.setProperty("annotators","tokenize, ssplit, pos");

        frenchPOSpipeline = new StanfordCoreNLP(frenchProps);


        Properties enProps = new Properties();
        enProps.setProperty("annotators","tokenize, ssplit, pos");

        enPOSpipeline = new StanfordCoreNLP(enProps);
        try {
             enWordVectors = WordVectorSerializer.loadTxtVectors(new File("local_directory/resources//embeddings/glove.6B.300d.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        fillerWordsFrench = null;
        try {
            fillerWordsFrench = Files.readAllLines(Paths.get("local_directory/resources/filler_words_french.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        fillerWordsEnglish = null;
        try {
            fillerWordsEnglish = Files.readAllLines(Paths.get("local_directory/resources/filler_words.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stopWordsFrench = null;
        try {
            stopWordsFrench = Files.readAllLines(Paths.get("local_directory/resources/custom_stopwords_full_french.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stopWordsEnglish = null;
        try {
            stopWordsEnglish = Files.readAllLines(Paths.get("local_directory/resources/custom_stopwords_full.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            stopWordsFrench2 = Files.readAllLines(Paths.get("local_directory/resources/stopwords_french.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}