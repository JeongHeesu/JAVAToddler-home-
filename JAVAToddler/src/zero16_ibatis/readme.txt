1.사용될 속성명
	 mode : 프로시저, 펑션 개체 사용시의 변수값 입출력 방향 대문자 IN, OUT, INOUT
	 javaType : java.lang.* 내의 기본 데이터 타입.
	 jdbcType : java.sql.Types 내에 정의된 jdbc jar를 활용해 접근하는
	            RDB의 column 타입.
	               대문자로 설정.
	               컬럼의 데이터타입이 number일경우 javaType=long,jdbcType=DECIMAL or NUMERIC으로 설정해야함.
	 nullValue : null값 대체 값 특정 더미값 또는 'NO_ENTRY'
	 
	 참고 : http://www.mybatis.org/core/configuration.html#typeHandlers 에서 javaType과 jdbcType 확인

 2.ResultSet(Cursor) 반환
 
 3.데이타베이스 객체 생성
 
 4.insert 구문 확장
   