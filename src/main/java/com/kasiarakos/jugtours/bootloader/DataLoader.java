package com.kasiarakos.jugtours.bootloader;

import com.kasiarakos.jugtours.model.Event;
import com.kasiarakos.jugtours.model.Group;
import com.kasiarakos.jugtours.repos.GroupRepository;
import org.springframework.boot.CommandLineRunner;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

public class DataLoader implements CommandLineRunner {

    private final GroupRepository repository;

    public DataLoader(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
                "Richmond JUG").forEach(name ->
                repository.save(Group.builder().name(name).build())
        );

        Group djug = repository.findByName("Denver JUG");
        Event e = Event.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println);
    }
}
