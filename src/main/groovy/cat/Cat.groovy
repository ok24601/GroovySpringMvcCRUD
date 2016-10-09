package cat

import groovy.transform.ToString
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "cat")
@ToString
class Cat {
    @Id
    int age
    @Column
    String name
}
