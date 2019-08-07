package springboot.ebay.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ebay.exam.model.SchemaEntity;

public interface ShortenRepository extends JpaRepository<SchemaEntity, String> {
    
}