package com.example.demo.factory;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;
import com.example.demo.pojo.Connection;
import com.example.demo.pojo.Response;

/**
 * @author Tarun.Jadav
 */
@Component
public class BuildFactory {

	private static final Map<Integer, Response> INSTANCEMAP;
	static {
		INSTANCEMAP = new ConcurrentHashMap<Integer, Response>();
	}
	private static final int DEFAULT_NEW_SET_SIZE = 5;

	private static final int MAX_INSTANCE_SET_SIZE = 10;

	private static final short DEFAULT_SORT_SIZE = 0;

	/**
	 * @param DefaultNewSetSize - variable {@summary gives set of 5 and give status
	 *                          available}
	 */
	private void buildNextNoOfSet(int DefaultNewSetSize) {
		final int INITIAL_SIZE = INSTANCEMAP.size();

		for (int i = INITIAL_SIZE; i < (INITIAL_SIZE + DefaultNewSetSize); i++) {
			INSTANCEMAP.put(i, new Response(new Connection(), "available", DEFAULT_SORT_SIZE));

		}

	}

	/**
	 * @return updateInsatnce method {@summary checks the status If available then
	 *         return the updateInstance method}
	 */
	public Entry<Integer, Response> getInstance() {
		Stream<Entry<Integer, Response>> streamEntry;
		Entry<Integer, Response> entry;
		streamEntry = INSTANCEMAP.entrySet().parallelStream()
				.filter(e -> e.getValue().getStatus().equalsIgnoreCase("available"));

		if (INSTANCEMAP.size() < MAX_INSTANCE_SET_SIZE) {
			buildNextNoOfSet(DEFAULT_NEW_SET_SIZE);
		}
//FInd how to copy
		// For 1 Hour in-case not available then copy Line No 52
		if (streamEntry.count() > 0) {

			streamEntry = INSTANCEMAP.entrySet().parallelStream()
					.filter(e -> e.getValue().getStatus().equalsIgnoreCase("available"));
			entry = updateInstance(streamEntry.findFirst().get());
		} else {
			entry = assignMoreInstances();
		}
		return entry;

	}

	/**
	 * @param availableEntry - pre initialized instance entry {@summary status which
	 *                       are available change into in use}
	 */
	private static Entry<Integer, Response> updateInstance(Entry<Integer, Response> availableEntry) {
		Response response = availableEntry.getValue();
		response.setStatus("in-use");
		response.increaseNoOfCount();
		INSTANCEMAP.replace(availableEntry.getKey(), response);
		return availableEntry;
	}

	/**
	 * {@summary print all status}
	 */
	public void printAllEntryStatus() {
		System.out.println("________________________________________________________");
		System.out.println(INSTANCEMAP.size());
		INSTANCEMAP.entrySet().forEach(System.out::println);
	}

	/**
	 * {@summary remove available status}
	 */
	private void decreaseNoOfInstance() {
		INSTANCEMAP.entrySet().removeIf(e -> e.getValue().getStatus().equalsIgnoreCase("available"));
	}

	/**
	 * @param availableEntry - pre initialized instance entry {@summary change the
	 *                       status in available which are already in use }
	 */
	public void freeInstance(Entry<Integer, Response> availableEntry) {

		if (INSTANCEMAP.size() < MAX_INSTANCE_SET_SIZE) {
			Response response = availableEntry.getValue();
			response.setStatus("available");
			response.decreaseNoOfCount();
			INSTANCEMAP.put(availableEntry.getKey(), response);
			if (INSTANCEMAP.entrySet().parallelStream()
					.filter(e -> e.getValue().getStatus().equalsIgnoreCase("available")).count() == 10) {
				decreaseNoOfInstance();
			}
		} else {

			decreaseNoOfConn(availableEntry);
		}

	}

	/**
	 * @return EntrySet
	 */
	private Entry<Integer, Response> assignMoreInstances() {
		Entry<Integer, Response> entry;
		System.out.println("look here");

		entry = INSTANCEMAP.entrySet().parallelStream()
				.sorted((o1, o2) -> Integer.compare(o1.getValue().getNoOfConn(), o2.getValue().getNoOfConn()))
				.findFirst().get();
		System.out.println(entry);

		increaseNoOfCon(entry);

		return entry;

	}

	/**
	 * 
	 * @param assignNew {@summary increases the no. of users in single connection and increases the count of users in conn}
	 * @return EntrySet
	 */
	private Entry<Integer, Response> increaseNoOfCon(Entry<Integer, Response> assignNew) {

		assignNew.getValue().increaseNoOfCount();
		assignNew.getValue().setStatus("in-use");
		INSTANCEMAP.replace(assignNew.getKey(), assignNew.getValue());
		System.out.println(assignNew);

		return assignNew;
	}

	/**
	 * 
	 * @param availableEntry
	 * 
	 *                       {@summary if conn has more than one user than will
	 *                       decrese no. of connection}
	 */

	private void decreaseNoOfConn(Entry<Integer, Response> availableEntry) {

		
		availableEntry.getValue().decreaseNoOfCount();
		if (availableEntry.getValue().getNoOfConn() == 0) {

			availableEntry.getValue().setStatus("available");
		}
		INSTANCEMAP.replace(availableEntry.getKey(), availableEntry.getValue());

	}

}
