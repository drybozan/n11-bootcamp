package com.n11.n11bootcamp.general;

import com.n11.n11bootcamp.exceptions.ItemNotFoundException;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/*

Tüm nesenler için ortak db operasyonları generic şekilde bu class içinde gerçekleşir.
 */
@Getter
public abstract class BaseEntityService<E extends BaseEntity, R extends JpaRepository<E, Long>> {

    private final R repository;

    protected BaseEntityService(R repository) {
        this.repository = repository;
    }

    public E save(E entity) {


        //setCreatorId() ve setUpdaterId() set edebilmek için login olmuş bir kullanıcı bilgisi gerekli

        BaseAdditionalFields baseAdditionalFields = entity.getBaseAdditionalFields();
        if (baseAdditionalFields == null) {
            baseAdditionalFields = new BaseAdditionalFields();
        }

        LocalDateTime now = LocalDateTime.now();
      /*  if (entity.getId() == null) {
            // yeni kayıt
            baseAdditionalFields.setCreateDate(now);
            //entity.getBaseAdditionalFields().setCreatorId();
        }
*/
        // guncelleme

        baseAdditionalFields.setUpdateDate(now);
        //entity.getBaseAdditionalFields().setUpdaterId();
        entity.setBaseAdditionalFields(baseAdditionalFields);

        entity = repository.save(entity);
        return entity;
    }

    public List<E> findAll() {
        return repository.findAll();
    }

    public E findByIdWithControl(Long id) {
        Optional<E> optionalE = repository.findById(id);
        E entity;
        if (optionalE.isPresent()) {
            entity = optionalE.get();
        } else {
            throw new ItemNotFoundException(GeneralErrorMessage.ITEM_NOT_FOUND);
        }

        return entity;
    }

    public Optional<E> findById(Long id){
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void delete(E entity){
        repository.delete(entity);
    }

    public boolean existById(Long id){
        return repository.existsById(id);
    }

}