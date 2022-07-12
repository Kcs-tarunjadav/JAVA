
package com.example.demo;

import java.util.Map.Entry;
import com.example.demo.factory.BuildFactory;
import com.example.demo.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Tarun.Jadav
 */
@Component
public class CheckStatus {
	@Autowired
	private BuildFactory factory;

	// Set 5
	// max limit 10

	/**
	 * {@summary perform the operations on status }
	 */
	public void execute() {
		Entry<Integer, Response> entry0, entry1, entry2, entry3, entry4, entry5, entry6, entry7, entry8, entry9,
				entry10, entry11, entry12, entry13, entry14, entry15, entry16;

		entry0 = factory.getInstance();
		entry1 = factory.getInstance();
		entry2 = factory.getInstance();
		entry3 = factory.getInstance();
		entry4 = factory.getInstance();
		entry5 = factory.getInstance();
		entry6 = factory.getInstance();
		entry7 = factory.getInstance();
		entry8 = factory.getInstance();
		entry9 = factory.getInstance();
		entry10 = factory.getInstance();
		entry11 = factory.getInstance();
		entry12 = factory.getInstance();
		entry13 = factory.getInstance();
		entry14 = factory.getInstance();

		factory.freeInstance(entry6);
		entry15 = factory.getInstance();
		factory.freeInstance(entry15);
		factory.freeInstance(entry12);

		entry16 = factory.getInstance();

		factory.printAllEntryStatus();

	}

}
