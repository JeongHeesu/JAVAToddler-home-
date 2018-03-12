package zero28.akka.a08.akkaServerContect.server;

import com.typesafe.config.ConfigFactory;
import akka.actor.ActorSystem;
import akka.kernel.Bootable;

// Bootable 구현시 디폴트 생성자를 활용해야함.
public class RemoteSystem implements Bootable {

	final ActorSystem remoteSystem = ActorSystem.create("remoteSystem",
			ConfigFactory.load().getConfig("serverConfig"));
	
	public RemoteSystem() {
		System.out.println("서버 생성자");
	}

	@Override
	public void startup() {
//      application.cofig 파일로부터 읽어온 서버 설정 정보		
//		Config(
//		          SimpleConfigObject(
//		                {"akka":
//		                     {"remote":
//		                          {"transport":
//		                                 "akka.remote.netty.NettyRemoteTransport",
//		                                 "netty":
//		                                       {"port":2553,
//		                                         "hostname":"127.0.0.1"
//		                                       }
//		                          }
//		                      }
//		                }
//		         )
//		)
		System.out.println("server started");
	}
	
	@Override
	public void shutdown() {
		remoteSystem.shutdown();
	}
}
