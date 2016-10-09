package cat

import org.springframework.data.jpa.repository.JpaRepository

interface CatRepository extends JpaRepository<Cat, Integer>{

}