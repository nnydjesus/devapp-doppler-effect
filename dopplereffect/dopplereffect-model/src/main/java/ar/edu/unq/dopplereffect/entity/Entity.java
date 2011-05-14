package ar.edu.unq.dopplereffect.entity;

public class Entity implements IEntity {
    private static final long serialVersionUID = 1L;

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
