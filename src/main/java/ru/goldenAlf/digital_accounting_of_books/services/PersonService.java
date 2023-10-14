package ru.goldenAlf.digital_accounting_of_books.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.goldenAlf.digital_accounting_of_books.model.Person;
import ru.goldenAlf.digital_accounting_of_books.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Primary
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //список всех людей
    public List<Person> index() {
        return personRepository.findAll();
    }

    //Возвращает конкретного человека по id
    public Optional<Person> show(int id) {
        return personRepository.findById(id);
    }

    //Возвращает конкретного человека по имени
    public Optional<Person> show(String fullName) {
        return personRepository.findByFullName(fullName);
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        personRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Person personUpdate) {
        Person person = personRepository.findById(id).get();
        person.setFullName(personUpdate.getFullName());
        person.setBirthday(person.getBirthday());
        person.setBookList(personUpdate.getBookList());
    }
}
