package cat

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CatService {

    @Autowired
    CatRepository repository

    List<Cat> getAllCats() {
        return repository.findAll()
    }
}
