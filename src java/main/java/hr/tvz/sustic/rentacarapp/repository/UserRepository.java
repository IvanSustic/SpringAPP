package hr.tvz.sustic.rentacarapp.repository;


import hr.tvz.sustic.rentacarapp.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsername(String username);


}
