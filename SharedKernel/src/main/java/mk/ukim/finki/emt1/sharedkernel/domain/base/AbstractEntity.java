package mk.ukim.finki.emt1.sharedkernel.domain.base;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public class AbstractEntity<ID extends DomainObjectId> {
    @EmbeddedId
    private ID id;

    public <ID extends DomainObjectId> AbstractEntity(ID id) {

    }
    protected AbstractEntity() {
    }
}
