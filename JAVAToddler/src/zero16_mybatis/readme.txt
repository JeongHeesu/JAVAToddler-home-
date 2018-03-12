MyBatis(열린 세션[컨넥션과 트랜잭션]은 반드시 닫는다.)

1. 정의 : MyBatis는 개발자가 지정한 SQL, 저장프로시저 그리고 몇가지 고급 매핑을 지원하는 iBatis상위 버젼의
          퍼시스턴스 프레임워크.
         
2.iBatis : MyBatis 설정 파일 비교
            iBatis                                                                            |     MyBatis
     설정    xml                                                                              |      xml or java code or Annotation
     내용    <?xml version="1.0" encoding="UTF-8" ?>                                          |     <?xml version="1.0" encoding="UTF-8" ?>
	         <!DOCTYPE sqlMapConfig                                                           |     <!DOCTYPE configuration
	           PUBLIC "-//ibatis.apache.org//DTD SQL Map Config 2.0//EN"                      |       PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
	           "http://ibatis.apache.org/dtd/sql-map-config-2.dtd">                           |   	  "http://mybatis.org/dtd/mybatis-3-config.dtd">	         <sqlMapConfig>                                                                   |   	<configuration>	         	<properties resource="examples/sqlmap/maps/SqlMapConfigExample.properties" /> | 		<properties resource="examples/sqlmap/maps/SqlMapConfigExample.properties" />                 <settings cacheModelsEnabled="true" enhancementEnabled="true"                |		    <typeAliases>                           lazyLoadingEnabled="true" maxRequests="128" maxSessions="10"       |         	<typeAlias alias="memberVO" type="zero16_ibatis.bean.MemberVO"/>                           maxTransactions="5" useStatementNamespaces="false"/>               |		    	<typeAlias alias="cartVO" type="zero16_ibatis.bean.CartVO"/>                 <transactionManager type="JDBC" >                                            |		    </typeAliases>         			<dataSource type="SIMPLE">                                                |		     <environments default="development">         				<property name="JDBC.Driver" value="${driver}"/>                      |		 		<environment id="development">         				<property name="JDBC.ConnectionURL" value="${url}"/>               	  |      			<transactionManager type="JDBC" />         				<property name="JDBC.Username" value="${username}"/>                  |      			<dataSource type="POOLED">         				<property name="JDBC.Password" value="${password}"/>                  |  	 				<property name="driver" value="${driver}" />         			</dataSource>                                                             |		 				<property name="url" value="${url}" />         		</transactionManager>                                                         |    	  				<property name="username" value="${username}" />         		<sqlMap resource="examples/sqlmap/maps/Person.xml" />                         |        				<property name="password" value="${password}" />	         </sqlMapConfig>                                                                  |   	   			</dataSource>
                                                                                              |   	 		</environment>
	                                                                                          |   	 	</environments>
	                                                                                          |       	<mappers>			
                                                                                              |       		<mapper resource="examples/sqlmap/maps/Person.xml" />
                                                                                              |        	</mappers>	
                                                                                              |     </configuration>
                                                                                                    * 각 Mapper에 존재하던 <typeAlias는 설정파일의 <typeAliases>로 팩키징 처리.

4.iBatis : MyBatis Mapper 비교
	       iBatis                                                                             |     MyBatis
     설정    xml                                                                              |      xml or java code
     내용   <?xml version="1.0" encoding="UTF-8"?>                                            |      <?xml version="1.0" encoding="UTF-8" ?>
			<!DOCTYPE sqlMap                                                                  |      <!DOCTYPE mapper
			  PUBLIC "-//ibatis.apache.org//DTD SQL Map 2.0//EN"                              |        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
			  "http://ibatis.apache.org/dtd/sql-map-2.dtd">                                   |        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
			<sqlMap namespace="member">                                                       |      <mapper namespace="팩키지명.맵퍼명">
				<typeAlias alias="memberVO" type="zero16_ibatis.bean.MemberVO"/>              |          <sql id="memberAll">         
				<typeAlias alias="cartVO" type="zero16_ibatis.bean.CartVO"/>                  |      		SELECT * FROM MEMBER             
	            <sql id="memberAll">                                                          |          </sql>                     
					SELECT * FROM MEMBER                                                      |		   	<select id="getMemberInfo" parameterType="map" resultType="memberVO">
				</sql>                                                                        |           	<include refid="memberAll"/>    
				<select id="getMemberInfo" parameterClass="map" resultClass="memberVO">       |          	WHERE MEM_ID = #{mem_id}
					<include refid="memberAll"/>                                              |          	AND MEM_PASS = #{mem_pass}           
					WHERE MEM_ID = #mem_id#                                                   |          </select>  
					  AND MEM_PASS = #mem_pass#	                                              |       </mapper> 
				</select>                                                                     |     
	        </sqlMap>                                                                         |     
	 
5. iBatis : MyBatis 쿼리 실행 자원
          iBatis               MyBatis
   자원명 SqlMapClient         SqlSession
   
6. iBatis : MyBatis 쿼리 실행 메서드
          iBatis               MyBatis
   조회   queryForObject       selectOne
          queryForList         selectList
          queryForMap          selectMap
   입력   insert               insert
   수정   update               update
   삭제   delete               delete
   
7. iBatis : MyBatis 매퍼 내 선언
             iBatis               MyBatis
   파라메터  parameterClass       parameterType
   반환값    resultClass          resultType
          
8. iBatis : MyBatis 매퍼 내 파라메터 맵핑
             iBatis               MyBatis
   패턴      #mem_id#             #{mem_id}         
        
9. iBatis : MyBatis Dynamic Sql 구성
             iBatis               MyBatis
   패턴      <Dynamic>            <if></if> : where절에서 활용 
                <isNotEmpty>      <choose> <when></when> <otherwise></otherwise> </choose> : 자바의 switch문
                <isNull>          <where> : where절을 동적으로 구성
                <isEqual>                   <where> <if></if> | <choose> <when></when> <otherwise></otherwise> </choose> </where>  
             .....                          <where>는 내부적인 분기를통해 추가되는 AND or OR로 시작되는 추가 쿼리에서 AND와
                                            OR를 삭제.
                                  <trim prefix="where" prefixOverrides="AND 또는 OR"/> .. </trim> : <where>의 AND or OR의 삭제를
                                                                                                   극복.
                                  <set> <if></if> | <choose> <when></when> <otherwise></otherwise> </choose> </set> : update의
                                                                                                    set절을 동적으로 구성하며
                                                                                                    추가되는 쿼리의 선두에 존재하는
                                                                                                    ,를 제거.
                                  <trim prefix="set" prefixOverrides=","/> .. </trim> : <set>의 , 삭제를 극복.
             <iterator>           <foreach collection="배열값" item="요소" index="i" open="(" separator="," close=")">
                                          #{요소}
                                  </foreach> 

10. iBatis : MyBatis 프로시저,펑션 호출
            iBatis                       MyBatis
    패턴    procedure                    update statementType="CALLABLE" 선언.
            (프로시저,펑션)              (프로시저, 펑션)
    Dao     queryForObject("객체명")     update("객체명")
             
11. 라이브러리 다운로드
   3.1 http://blog.mybatis.org/p/products.html
          mybatis-3.2.2.zip 다운로드
          
          
          
          
          
          
          
          
          
          
           