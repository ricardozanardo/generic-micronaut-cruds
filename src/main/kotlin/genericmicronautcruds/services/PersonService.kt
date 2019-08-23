package genericmicronautcruds.services

import genericmicronautcruds.models.Person
import genericmicronautcruds.repository.PersonRepository
import javax.inject.Singleton

@Singleton
class PersonService(personRepository: PersonRepository): GenericService<Person>(personRepository) {

    //    fun newPatient(patient: Patient): Response {
//        return try {
//            val newPatient = repository.insert(patient)
//            Response(listOf<Any>(newPatient!!), 201, "Pacient inserted.")
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//            Response(null, 500, ex.message!!)
//        }
//    }
//
//    fun get(params: Map<String, Any>?): List<Patient> {
//        return repository.get(params)
//    }

}