package cu.uclv.postgrade.backend.repository;

import cu.uclv.postgrade.backend.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {
    List<University> findByNameContaining(String name);
}
