package com.company.ReservationApp;

import com.company.ReservationApp.api.ReservationApiController;
import com.company.ReservationApp.api.UserApiController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReservationAppApplication {

	public static void main(String[] args) {
		UserApiController userApiController = new UserApiController();
		ReservationApiController reservationApiController = new ReservationApiController();

		SpringApplication.run(ReservationAppApplication.class, args);
	}

}
