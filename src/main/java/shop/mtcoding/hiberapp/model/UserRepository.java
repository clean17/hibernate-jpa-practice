package shop.mtcoding.hiberapp.model;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    // @Transactional
    // public void save(User user){
    //     em.persist(user);
    // }
    // 저장후 리턴이 필요할 경우 이렇게 사용하면 된다. 물론 DTO를 만들어서 리턴하면 더 좋다.
    @Transactional
    public User save(User user){
        em.persist(user);
        return user;
    }
    // @Transactional
    // public void update(User user){
    //     em.merge(user);
    // }
    @Transactional
    public User update(User user){ // jpa 의  update 는 pk를 주지 않으면 insert를 해버린다. !!!!! 주의 
        return em.merge(user);
    }
    
    @Transactional
    public void delete(User user){
        em.remove(user);
    }
    public User findById(Long id){
        return em.find(User.class, id);
    }
    public List<User> findAll(){
        return em.createQuery("select u from User u", User.class)
                    .getResultList();
    }
    public List<User> findAll(int page){
        return em.createQuery("select u from User u", User.class)
                    .setFirstResult(page * 5)
                    .setMaxResults(5)
                    .getResultList();
                    // 이렇게 사용시 자동적으로 페이징이 된다. 처음은 0 페이지 , 2개씩 보겠다.
    }
    public List<User> findAll(int page, int row){
        return em.createQuery("select u from User u", User.class)
                    .setFirstResult(page * row)
                    .setMaxResults(row)
                    .getResultList();
                    // 오버로딩해서 추가할 수 밖에..
    }
}
