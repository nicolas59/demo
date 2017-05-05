package fr.nro.demo.hazelcash;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;

public class GettingStartedClient {

	static ClientConfig prepareConfig() {
		ClientConfig clientConfig = new ClientConfig();

		clientConfig.getNetworkConfig().addAddress("127.0.0.1");
		clientConfig.getNetworkConfig().setConnectionTimeout(5000);
		// clientConfig.getNetworkConfig().set¢
		return clientConfig;
	}

	public static void main(String[] args) {
		ClientConfig clientConfig = prepareConfig();

		HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
		IMap map = client.getMap("customers");
		System.out.println("Map Size:" + map.size());

		client.getMap("customers").put(4, "Nicolas");

		client.getMap("customers").forEach((key, value) -> System.out.println(value));

		// recuperation de la queue
		IQueue queues = client.getQueue("customers");
		System.out.println(queues.peek());
		System.out.println(queues.poll());
		System.out.println(queues.peek());

		client.shutdown();
	}

}
