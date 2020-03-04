package mate.academy.review.analysis.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import mate.academy.review.analysis.entity.Review;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

@Slf4j
public class CsvParserUtils {
    public static List<Review> parseCsv(File file) {
        try {
            log.info("Reading file...");
            Reader in = new FileReader(file);
            CSVParser parser = new CSVParser(in, CSVFormat.DEFAULT
                    .withHeader("Id", "ProductId", "UserId", "ProfileName",
                            "HelpfulnessNumerator", "HelpfulnessDenominator",
                            "Score", "Time", "Summary", "Text"));
            return createReview(parser);
        } catch (IOException e) {
            throw new RuntimeException("Can't parse reviews from file", e);
        }
    }

    private static List<Review> createReview(CSVParser records) {
        List<Review> reviews = new ArrayList<>();
        for (CSVRecord record : records) {
            Review review = new Review();
            review.setProductId(record.get("ProductId"));
            review.setUserId(record.get("UserId"));
            review.setProfileName(record.get("ProfileName"));
            review.setSummary(record.get("Summary"));
            review.setText(record.get("Text"));
            reviews.add(review);
        }
        return reviews;
    }

}
