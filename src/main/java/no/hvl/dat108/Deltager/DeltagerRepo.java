package no.hvl.dat108.Deltager;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeltagerRepo extends JpaRepository<Deltager, String> {
	// Autogenerert
	Deltager findByMobil(String mobil);
}
