package jpabook.jpashop.domain.Item;

import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import static javax.persistence.InheritanceType.*;

@Entity
@Getter @Setter
@Inheritance(strategy = SINGLE_TABLE) //상속 세팅!!
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany //다대다 매핑 예시!! ,but 실무에서 쓰지말자.
    @JoinTable(name = "category_item", //중간테이블에 있는 category_id
                joinColumns = @JoinColumn(name = "category_id"),
                inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Category> categories = new ArrayList<>();


    //내 부모니까? 셀프 양방향 연관관계... ㅠ어렵
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

}
