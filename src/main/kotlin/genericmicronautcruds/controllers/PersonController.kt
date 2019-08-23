package genericmicronautcruds.controllers

import io.micronaut.http.annotation.Controller
import genericmicronautcruds.models.Person
import genericmicronautcruds.services.PersonService

@Controller("/patient")
class PersonController(personService: PersonService) : GenericController<Person>(personService) {


}