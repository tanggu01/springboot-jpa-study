package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //양방향 연관관계이니 주인을 정해줘야한다. 객체는 둘다 업데이트지만 테이블에선 fk 1개를 업데이트
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id") //매핑, fk를 뭘로할건지! (fk 이름적기)
    private Member member;


    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //Order 저장시 orderItems 도 같이저장!!
    private List<OrderItem> orderItems = new ArrayList<>();
    /*
    //cascade X:
    persist(orderItemA)
    persist(orderItemB)
    persist(orderItemC)
    persist(order)
    //cascade O:
    persist(order)
     */


    //access를 많이하는곳, 뭘 더 많이 조회하냐? order 를 보며 delivery를 찾기때문에 order에다가 Delivery id FK 를 넣는다.
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태: [ORDER, CANCEL]

    //==연관관계 메서드==// 위치: 핵심적으로 컨트롤하는곳
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    } //그뒤에 order.setMember(member);

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
