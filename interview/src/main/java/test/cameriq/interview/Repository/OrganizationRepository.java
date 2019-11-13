package test.cameriq.interview.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.cameriq.interview.Models.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer> {
}
