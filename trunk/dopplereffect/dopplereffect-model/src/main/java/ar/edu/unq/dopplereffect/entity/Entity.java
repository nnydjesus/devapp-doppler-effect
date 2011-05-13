package ar.edu.unq.dopplereffect.entity;

public class Entity implements IEntity {

    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(final Long id) {
        this.id = id;
    }
}
