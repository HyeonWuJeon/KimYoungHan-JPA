package com;

import com.hellojpa.domain.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); //팩토리생성
        EntityManager em = emf.createEntityManager(); //팩토리 사용
        EntityTransaction tx = em.getTransaction(); //트랜잭션 사용
        tx.begin();



        try{

            Member member = new Member();
            member.setName("test");
            member.setId(1L);
            em.persist(member);

            TypedQuery<Member> query =em.createQuery("select m from Member m", Member.class); //타입정보를 확실히 알 경우
            System.out.println("query = " + query);
////            Query query2 =em.createQuery("select m.name,m.id from Member m", Member.class); //타입정보를 받을수 없을때
//
//            //결과가 하나이상일때
//            List<Member> member = query.getResultList(); //결과없을시 빈 리스트반환
//            System.out.println("member = " + member);
//            for(Member m: member){
//                System.out.println(m.getName());
//            }
//            //결과가 하나일때
//            Object result = query2.getSingleResult(); //결과 없을시에 : NoResultException
//                                                      //둘 이상 : NotUniqueResult
//            System.out.println(result);
//
//            //Spring Data Jpa 에서는 NoResultException을 방지하기위해 getSingleResult를 try catch 해준다
//
//            //이름 기준 바인딩
//            TypedQuery<Member> query3 =em.createQuery("select m from Member m where m.name = :name", Member.class);
//            query3.setParameter("name", "kim");
//            Member SingleResult = query3.getSingleResult();
//            System.out.println(SingleResult.getName());
//
//            //위치기준 바인딩
//            TypedQuery<Member> query4 =em.createQuery("select m from Member m where m.name = ?1", Member.class);
//            query4.setParameter(1,"kim");
//            Member SingleResult2 = query3.getSingleResult();
//            System.out.println(SingleResult.getName());
        tx.commit();
        }catch(Exception e){
            tx.rollback();
        } finally{
            em.close();
        }

    }


}













































/*



        /**개념
         * emf? : 엔티티 매니저 팩토리는애플리케이션 로딩시점에 하나만만들어두면된다
         * 웹애플리케이션 서비스시 웹서버가 올라오는 시점에 하나만 생성된다.
         * em? : 고객의 요청이 올때마다 계속 사용된다.
         * em의 경우 절대 쓰레드간의 공유x (사용하고 버려야된다)
         * Jpa의 모든 데이터변경은 트랜잭션 안에서 실행

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        //실제 DB에저장하는 트랜잭션단위 (SERIVCE) 할때는 엔티티매니저를 꼭 만들어줘야한다.
        EntityManager em = emf.createEntityManager(); //데이터베이스 커넥션을 받았다고생각

        //트랜잭션생성
        EntityTransaction tx = em.getTransaction();
        tx.begin();
//스프링이 전부해결해준다.

        //  자바 collection
        try {

            Member member = new Member();


             * insert
            1. 비영속 상태
            Member member = new Member();
            member.setId(2L);//로우 값
            member.setName("HelloB");

             2.영속성 컨텍스트에 저장
             em.persist(member); 디비에 저장이 안됨.

             3.영속성 컨텍스트에서 분리.
             em.detach(member);

             4. db에서 삭제
           em.remove(member);



            // find
            Member findMember = em.find(Member.class,1L);
            System.out.println("findMember.id = " + findMember.getId());
            System.out.println("findMember.name = " + findMember.getName());

             // delete
             em.remove(findMember);


            /** update
             * 트랜잭션 커밋시점에 변경사항을 jpa가 체크한다.
             * 변경사항이 있을경우 트랜잭션 커밋직전에 update 쿼리를 날린다.

            Member findMember = em.find(Member.class, 1L);
            findMember.setName("HelloJPA");

            em.persist(findMember); 저장 하지않아도 된다!
             */


            /**
             * JPQL
             * 엔티티 객체를 대성으로 쿼리
             * SQL
             * 데이터 베이스 테이블 대상 쿼리
             * 애플리케이션이 필요한 데이터만 db에서 불러오려면 결국 검색 조건이 포함된 sql문 사용

//             List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                     .setFirstResult(5)
//                     .setMaxResults(8)
//                    .getResultList();
//              for(Member member : result){
//               System.out.println(member.getName());
//              }

            tx.commit(); //해당시점에 db쿼리가 날라간다. //트랜잭션이끝


        } catch(Exception e) {
            tx.rollback();
        }finally

        {
            em.close();
        }

        emf.close(); //웹어플리케이션시 왓스가 내려갈때 emf를 닫아준다. 리소스 릴리즈시킴

    }

}

/**
 * persist : db에저장하는것이 아닌 영속성 컨텍스트에 저장하는것이다.
             */