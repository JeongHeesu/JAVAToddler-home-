package zero11_zacksB;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// 대상 xml 파일의 최상위 root element명으로 작성된 클래스.
// 클래스 명과 root element명이 상이한 경우
// @XmlRootElement(name="root element명") 선언  
@XmlRootElement
public class Members {
	// root element 하위에 존재하는 자식 element를 가지는 member element.
	// element명 member와 선언된 레퍼런스 변수명 상이함으로 name 속성 선언.
	@XmlElement(name="member")
	public List<MemberVO> members;
	
	// root element 하위에 존재하는 자식 element를 가지지않은 total element
	@XmlElement
	public String total;
}
