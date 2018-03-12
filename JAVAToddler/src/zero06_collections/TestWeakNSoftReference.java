package zero06_collections;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

// 객체 cache 처리.
public class TestWeakNSoftReference {
	// 일반적인 인스턴스화된 객체의 레퍼런스 타입의 선언과 GC에의한 객체 정리.
	// 참조변수없이 컬렉션내에 요소로 추가된 객체의 인스턴스는 컬렉션이 레퍼런싱하게되어 해당 
	// 컬렉션의 GC 처리전까지 메모리 릭(메모리 누수)의 원인이 됨.
	// GeneralReference or StrongReference로 불림.
	public void generalReference() {
        List<MessageEntity> list = new ArrayList<MessageEntity>();
        
        // MessageEntity 참조 변수가 없음.
        list.add(new MessageEntity("I'm alive...GeneralReference!"));

        // 일반적으로 인스턴스화된 객체의 메모리 참조 변수의 갯수가 0일때 GC 대상이 됨.
        // Heap 메모리의 관리주체인 JVM에 가비지컬렉션을 의뢰.
        System.gc();
        
        // System.gc();에의해 가비지컬렉션 의뢰를 받은 JVM에의해 정리될수도 있고 시간차를 두고 늦게될수도 있음.
        for (MessageEntity message : list) {
            if (message == null) System.out.println("MessageEntity Reference is null.");
            else System.out.println(message);
        }
    }

	// 객체를 컬렉션의 요소로 추가하여 컬렉션이 참조하고 있으면서 활용되지 않을시 해당 객체로 인한 메모리 누수가 발생됨.
	// StrongReference에비해 결합 강도가 약함.
	// 객체가 WeakReference를 제외하고 참조하는 곳이 존재 하지 않을 경우, Garbage Collection 대상이됨. 
    public void weakReference() {
        List<WeakReference<MessageEntity>> list = new ArrayList<WeakReference<MessageEntity>>();

        // WeakReference(약참조 객체)로 랩핑된 MessageEntity 객체는 WeakReference를 제외한 다른 참조변수가 존재할지 않을때
        // 가비지컬렉션 됨.
        WeakReference<MessageEntity> weakMessage = 
            new WeakReference<MessageEntity>(new MessageEntity("I'm alive...WeakReference!"));

        list.add(weakMessage);

        System.gc();

        for(WeakReference<MessageEntity> weakReference : list){
            if(weakReference == null){
            	System.out.println("WeakReference is null."); 
            }else{
               MessageEntity message = weakReference.get();
               if (message == null) System.out.println("MessageEntity reference is null.");
               else System.out.println(message);
            }
        }
    }
    
	// 객체를 컬렉션의 요소로 추가하여 컬렉션이 참조하고 있으면서 활용되지 않을시 해당 객체로 인한 메모리 누수가 발생됨.
	// 객체가 SoftReference를 제외하고 참조하는 곳이 존재 하지 않을 경우, 메모리의 여부공간 사이즈에따라 
    // Garbage Collection에 대상(메모리 사이즈에 여유없음.) 또는 대상에서의 제외(메모리 사이즈에 여유있음.) 됨.     
    public void softReference() {
        List<SoftReference<MessageEntity>> list = new ArrayList<SoftReference<MessageEntity>>();

        SoftReference<MessageEntity> weakMessage = 
            new SoftReference<MessageEntity>(new MessageEntity("I'm alive...SoftReference!"));

        list.add(weakMessage);

        System.gc();

        for(SoftReference<MessageEntity> softReference : list){
            if(softReference == null) System.out.println("reference is null.");
            else{
               MessageEntity message = softReference.get();
               if (message == null) System.out.println("MessageEntity reference is null.");
               else System.out.println(message);
            }
        }
    }
}

class MessageEntity {

    public String message;

    public MessageEntity() {}

    public MessageEntity(String message) {
           this.message = message;
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    @Override
    public String toString() {
           return "MessageEntity [message=" + message + "]";
    }
} 


