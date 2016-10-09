package cat

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class CatController {

    @Autowired
    CatService service;

    @RequestMapping("/cats")
    @ResponseBody List<Cat> getCats() {
        return service.getAllCats()
    }
}
