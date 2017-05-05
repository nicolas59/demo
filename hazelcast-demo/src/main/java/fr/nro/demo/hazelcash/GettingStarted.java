package fr.nro.demo.hazelcash;

import java.util.Map;
import java.util.Queue;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

public class GettingStarted {

	static Config prepareConfig() {
		Config cfg = new Config();

		cfg.getNetworkConfig().getJoin().getTcpIpConfig().addMember("127.0.0.1").setEnabled(true);
		cfg.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);

		return cfg;
	}

	static Config prepareConfigCLFromFile(String file) {
		Config cfg = new ClasspathXmlConfig(file);

		return cfg;
	}

	public static void main(String... args) {

		Config cfg = prepareConfigCLFromFile("hazelcast.xml");

		HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
		Map<Integer, String> mapCustomers = instance.getMap("customers");
		mapCustomers.put(1, "Joe");
		mapCustomers.put(2, "Ali");
		mapCustomers.put(3, "Avi");

		System.out.println("Customer with key 1: " + mapCustomers.get(1));
		System.out.println("Map Size:" + mapCustomers.size());

		Queue<String> queueCustomers = instance.getQueue("customers");
		queueCustomers.offer("Tom");
		queueCustomers.offer("Mary");
		queueCustomers.offer("Jane");
		System.out.println("First customer: " + queueCustomers.poll());
		System.out.println("Second customer: " + queueCustomers.peek());
		System.out.println("Queue size: " + queueCustomers.size());

	}
}
