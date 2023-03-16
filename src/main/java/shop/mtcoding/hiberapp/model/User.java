package shop.mtcoding.hiberapp.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "user_tb")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private Timestamp createdAt;

    // // @Setter 를 만들지 않는다 필요한 값만 넣어줄 생성자만 있으면 된다.
    // public User(String username, String password, String email){ 
    //     this.username = username;
    //     this.password = password;
    //     this.email = email;
    // }
    // 이렇게 만들지 말고

    // 이 방법이 너무 이쁘다
    @Builder
    public User(Long id, String username, String password, String email, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
    }

    // 필요한 데이터만 setter 로 바꿔준다.
    public void update(String password, String email){ 
        this.password = password;
        this.email = email;
    }


}
