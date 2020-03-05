package mate.academy.review.analysis.repository;

import mate.academy.review.analysis.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
