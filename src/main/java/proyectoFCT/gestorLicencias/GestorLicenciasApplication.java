package proyectoFCT.gestorLicencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import proyectoFCT.gestorLicencias.entity.Deportista;
import proyectoFCT.gestorLicencias.entity.Entrenador;
import proyectoFCT.gestorLicencias.entity.Especialidad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class GestorLicenciasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorLicenciasApplication.class, args);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ManyToMany");
		//Generamos un EntityManager
		EntityManager em = emf.createEntityManager();
		//Iniciamos una transacción
		em.getTransaction().begin();

		Deportista deportista1 = new Deportista("1234","49476970Y","Alberto Martinez", "20/05/2021", 12345L,"alberto@alberto.com");

		Entrenador entrenador1 = new Entrenador("1589","123456","Manolo Alonso","20/05/2021",123154L,"manolo@manolo.com");

		Entrenador juez1 = new Entrenador("1589","123456","Pepe Lopez","20/05/2021",123154L,"pepe@pepe.com");

		Especialidad latinos = new Especialidad("latinos");
		Especialidad caribeños = new Especialidad("caribeños");

		em.persist(deportista1);
		em.persist(entrenador1);
		em.persist(juez1);

		em.persist(latinos);
		em.persist(caribeños);

		em.flush();

		latinos.addPersona(deportista1,"C Nacional",true,false,false);
		latinos.addPersona(entrenador1,"Entrenador AI",false,true,false);

		caribeños.addPersona(juez1,"Juez nacional",false,false,true);

		em.flush();

		em.getTransaction().commit();

		em.close();
		emf.close();
	}
}


