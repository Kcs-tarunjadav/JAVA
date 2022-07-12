/**
 * Title: Connection Pooling
 * Date : 7/7/2022
 */
package com.example.demo;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @version 1.0.0
 * @author Tarun.Jadav
 * */
@SpringBootApplication
public class ConnectionPoolingTaskApplication {
           
    @Autowired
    CheckStatus status;
    
    
	/**
	 * @param args -program execution
	 */
	public static void main(String[] args) {
	  	SpringApplication.run(ConnectionPoolingTaskApplication.class, args);
	}
	/**
	 * {@summary call the execute method of CheckStatus class.}
	 */
	@PostConstruct
	public void run() {
		status.execute();
		
	}
		
	

}
