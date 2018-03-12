package zero28.akka.a08.akkaServerContect.client;

import java.util.ArrayList;
import java.util.List;

import zero28.akka.res.RemoteActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Address;
import akka.actor.Deploy;
import akka.actor.Props;
import akka.kernel.Bootable;
import akka.remote.RemoteScope;
import akka.routing.RoundRobinRouter;

import com.typesafe.config.ConfigFactory;

public class LocalSystem implements Bootable {
	final ActorSystem system = ActorSystem.create("localSystem", ConfigFactory
			.load().getConfig("localConfig"));
	
	private static final String AKKA_PROTOCOL = "akka";
	private static final String REMOTE_SYSTEM_NAME = "remoteSystem";
	private static final String REMOTE_HOST_IP = "127.0.0.1";
	private static final int REMOTE_HOST_PORT = 2553;

	@Override
	public void startup() {
		String message = "hello.";
		// ローカルのActorを生成
		System.out.println("local actor test.");

		ActorRef localActor = system.actorOf(Props.create(RemoteActor.class),
				"localActor");
		localActor.tell(message, null);

		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
		}

		System.out.println("-----");

		// リモートのActorを生成
		System.out.println("remote actor test.");
		ActorRef remoteActor = system.actorOf(Props.create(RemoteActor.class)
				.withDeploy(new Deploy(new RemoteScope(new Address(
						AKKA_PROTOCOL, REMOTE_SYSTEM_NAME, REMOTE_HOST_IP,
						REMOTE_HOST_PORT)))), "remoteActor");
		remoteActor.tell(message, null);

		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
		}
		System.out.println("-----");

		// ラウンドロビンでActorにメッセージを送信
		System.out.println("router test with RoundRobinRouter.");
		List<ActorRef> actors = new ArrayList<ActorRef>();
		actors.add(remoteActor);
		actors.add(localActor);

		ActorRef roundRobinRouter = system.actorOf(Props.create(
				RemoteActor.class).withRouter(RoundRobinRouter.create(actors)));

		for (int i = 0; i < 6; i++) {
			String roundRobinMessage = message + i;
			roundRobinRouter.tell(roundRobinMessage, null);
		}

		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
		}
		System.out.println("-----");

		system.shutdown();

		System.out.println("end.");
	}
	
	@Override
	public void shutdown() {
		system.shutdown();
	}
}
