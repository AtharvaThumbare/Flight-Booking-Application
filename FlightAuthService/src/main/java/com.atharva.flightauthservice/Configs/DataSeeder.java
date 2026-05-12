package com.atharva.flightauthservice.Configs;

import com.atharva.flightauthservice.Entity.Role;
import com.atharva.flightauthservice.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        if(roleRepository.findByRole("ROLE_ADMIN").isEmpty()) {

            Role admin = new Role();
            admin.setRole("ROLE_ADMIN");

            roleRepository.save(admin);
        }

        if(roleRepository.findByRole("ROLE_CUSTOMER").isEmpty()) {

            Role customer = new Role();
            customer.setRole("ROLE_CUSTOMER");

            roleRepository.save(customer);
        }

        if(roleRepository.findByRole("ROLE_AIRLINE_MANAGER").isEmpty()) {

            Role airlineManager = new Role();
            airlineManager.setRole("ROLE_AIRLINE_MANAGER");

            roleRepository.save(airlineManager);
        }
    }
}
